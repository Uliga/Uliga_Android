package com.uliga.app.provider

import com.uliga.app.base.LastActivityUtils
import com.uliga.app.ext.getGoogleSignInClient
import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginProvider
import com.uliga.domain.SocialLoginResult
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resumeWithException

@Singleton
class GoogleLoginProviderImpl @Inject constructor() : SocialLoginProvider {

    override suspend fun login(type: AuthType, checkedIdToken: String?): SocialLoginResult {
        // Just Mapping
        return SocialLoginResult(AuthType.GOOGLE, checkedIdToken!!)
    }

    override suspend fun logout(type: AuthType): Unit =
        suspendCancellableCoroutine { continuation ->
            getGoogleSignInClient(LastActivityUtils.requireLastActivity()).signOut()
                .addOnCompleteListener {
                    continuation.resumeWith(Result.success(Unit))
                    return@addOnCompleteListener
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                    return@addOnFailureListener
                }
        }

}