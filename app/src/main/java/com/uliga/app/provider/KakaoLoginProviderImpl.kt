package com.uliga.app.provider

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.uliga.app.base.LastActivityUtils
import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginProvider
import com.uliga.domain.SocialLoginResult
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resumeWithException

@Singleton
class KakaoLoginProviderImpl @Inject constructor() : SocialLoginProvider {

    override suspend fun login(type: AuthType, checkedIdToken: String?, checkedEmail: String?, checkedName: String?): SocialLoginResult {
        val authToken = loginWithKakao(LastActivityUtils.requireLastActivity()).getOrThrow()
        return accessTokenToResult(authToken.accessToken)
    }

    override suspend fun logout(type: AuthType): Unit =
        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.logout {
                it?.let {
                    continuation.resumeWithException(it)
                    return@logout
                }
                continuation.resumeWith(Result.success(Unit))
            }
        }


    private suspend fun loginWithKakao(context: Context): Result<OAuthToken> = runCatching {
        return@runCatching if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            loginWithKakaoWeb(context)

            //            loginWithKakaApp(context)
        } else {
            loginWithKakaoWeb(context)
        }
    }

    private suspend fun loginWithKakaApp(context: Context): OAuthToken =
        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->

                error?.let {
                    continuation.resumeWithException(it)
                    return@loginWithKakaoTalk
                }
                continuation.resumeWith(Result.success(token ?: return@loginWithKakaoTalk))
            }
        }


    private suspend fun loginWithKakaoWeb(context: Context): OAuthToken =
        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                error?.let {
                    continuation.resumeWithException(it)
                    return@loginWithKakaoAccount
                }

                token?.let {
                    continuation.resumeWith(Result.success(it))
                }

            }
        }

    private suspend fun accessTokenToResult(
        accessToken: String
    ): SocialLoginResult =
        suspendCancellableCoroutine { continuation ->
            UserApiClient.instance.me { user, error ->
                error?.let {
                    continuation.resumeWithException(it)
                    return@me
                }
                continuation.resumeWith(
                    Result.success(
                        SocialLoginResult(
                            type = AuthType.KAKAO,
                            token = accessToken,
                            name = user?.kakaoAccount?.name,
                            email = user?.kakaoAccount?.email
                        )
                    )
                )
            }
        }


}