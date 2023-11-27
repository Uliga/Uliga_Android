package com.uliga.domain.repository

import com.uliga.domain.model.userAuth.LoginResponse
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.model.userAuth.UserAuthDataExisted

interface UserAuthRepository {

    /**
     * Remote
     */

    suspend fun getUserAuthDataExisted(type: String, data: String): Result<UserAuthDataExisted>

    suspend fun postSocialLogin(socialLoginRequest: SocialLoginRequest): Result<LoginResponse>

    suspend fun postNormalLogin(normalLoginRequest: NormalLoginRequest): Result<LoginResponse>

    suspend fun getLogoutRedirect(): Result<String>

    /**
     * Local
     */

    suspend fun updateToken(accessToken: String): Result<Unit>

    suspend fun getToken(): Result<String>

    suspend fun updateId(id: Long): Result<Unit>

    suspend fun getId(): Result<Long>

    suspend fun updateIsLogin(isLogin: Boolean): Result<Unit>

    suspend fun getIsLogin(): Result<Boolean>
}