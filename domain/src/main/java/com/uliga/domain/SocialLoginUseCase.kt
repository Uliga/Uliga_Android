package com.uliga.domain

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocialLoginUseCase @Inject constructor(
    private val socialLoginProvider: SocialLoginProvider
){

    suspend operator fun invoke(authType: AuthType, checkedIdToken: String?): Result<LoginResult> {
        val socialLoginResult = socialLoginProvider.login(authType, checkedIdToken)
        Log.d("socialLoginResult", socialLoginResult.toString())

        return Result.success(LoginResult(""))
    }
}