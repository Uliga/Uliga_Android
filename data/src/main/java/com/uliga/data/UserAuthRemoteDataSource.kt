package com.uliga.data

import com.uliga.data.model.LoginResponseData
import com.uliga.data.model.SocialLoginRequestData
import com.uliga.data.model.UserAuthDataExistedData

interface UserAuthRemoteDataSource {

    suspend fun getUserAuthDataExisted(type: String, data: String): UserAuthDataExistedData

    suspend fun postSocialLogin(socialLoginRequestData: SocialLoginRequestData): LoginResponseData

}