package com.uliga.domain.usecase.userAuth.remote

import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginProvider
import com.uliga.domain.SocialLoginResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocialLoginUseCase @Inject constructor(
    private val socialLoginProvider: SocialLoginProvider
) {

    suspend operator fun invoke(
        authType: AuthType,
        checkedIdToken: String?,
        checkedEmail: String?,
        checkedName: String?
    ): Result<SocialLoginResult> {
        return runCatching {
            socialLoginProvider.login(authType, checkedIdToken, checkedEmail, checkedName)
        }
    }
}