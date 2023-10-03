package com.uliga.domain.repository

import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.SocialLoginRequest
import com.uliga.domain.model.UserAuthDataExisted

interface UserAuthRepository {

    /**
     * Remote
     */

    suspend fun getUserAuthDataExisted(type: String, data: String): Result<UserAuthDataExisted>

    suspend fun postSocialLogin(socialLoginRequest: SocialLoginRequest): Result<LoginResponse>

    /**
     * Local
     */

    suspend fun updateToken(accessToken: String): Result<Unit>

    suspend fun getToken(): Result<String>

    suspend fun updateId(id: Long): Result<Unit>

    suspend fun getId(): Result<Long>
}