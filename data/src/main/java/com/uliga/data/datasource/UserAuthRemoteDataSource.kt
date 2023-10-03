package com.uliga.data.datasource

import com.uliga.data.model.userAuth.LoginResponseData
import com.uliga.data.model.userAuth.NormalLoginRequestData
import com.uliga.data.model.userAuth.SocialLoginRequestData
import com.uliga.data.model.userAuth.UserAuthDataExistedData

interface UserAuthRemoteDataSource {

    suspend fun getUserAuthDataExisted(type: String, data: String): UserAuthDataExistedData

    suspend fun postSocialLogin(socialLoginRequestData: SocialLoginRequestData): LoginResponseData

    suspend fun postNormalLogin(normalLoginRequestData: NormalLoginRequestData): LoginResponseData

}