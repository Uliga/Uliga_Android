package com.uliga.domain.fakeRepository

import com.uliga.domain.model.userAuth.LoginMemberInfoResponse
import com.uliga.domain.model.userAuth.LoginResponse
import com.uliga.domain.model.userAuth.LoginTokenInfoResponse
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.model.userAuth.UserAuthDataExisted
import com.uliga.domain.repository.UserAuthRepository
import java.util.UUID
import kotlin.random.Random

class FakeUserAuthRepository: UserAuthRepository {

    private lateinit var loginResponse: LoginResponse

    private lateinit var accessToken: String

    private var id: Long = 0L

    override suspend fun getUserAuthDataExisted(
        type: String,
        data: String
    ): Result<UserAuthDataExisted> {
        TODO("Not yet implemented")
    }


    override suspend fun postSocialLogin(socialLoginRequest: SocialLoginRequest): Result<LoginResponse> {
        return runCatching { LoginResponse(
            memberInfo = LoginMemberInfoResponse(
                id = Random.nextLong(1L, Long.MAX_VALUE),
                email = socialLoginRequest.email,
                privateAccountBookId = Random.nextLong(1L, Long.MAX_VALUE),
                userName = socialLoginRequest.userName,
                nickName = socialLoginRequest.nickName,
            ),
            tokenInfo = LoginTokenInfoResponse(
                accessToken = UUID.randomUUID().toString(),
                grantType = UUID.randomUUID().toString(),
                accessTokenExpiresIn = Random.nextLong(1L, Long.MAX_VALUE)
            )
        ) }
    }

    override suspend fun postNormalLogin(normalLoginRequest: NormalLoginRequest): Result<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getLogoutRedirect(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun updateToken(accessToken: String): Result<Unit> {
        return runCatching {  this.accessToken = accessToken }
    }

    override suspend fun getToken(): Result<String> {
        return runCatching { accessToken }
    }

    override suspend fun updateId(id: Long): Result<Unit> {
        return runCatching { this.id = id }
    }

    override suspend fun getId(): Result<Long> {
        return runCatching { id }
    }

    override suspend fun updateIsLogin(isLogin: Boolean): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getIsLogin(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}