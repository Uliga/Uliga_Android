package com.uliga.data.repository

import com.uliga.data.datasource.UserAuthLocalDataSource
import com.uliga.data.datasource.UserAuthRemoteDataSource
import com.uliga.data.model.userAuth.toData
import com.uliga.data.model.userAuth.toDomain
import com.uliga.domain.model.userAuth.LoginResponse
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.model.userAuth.UserAuthDataExisted
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val remote: UserAuthRemoteDataSource,
    private val local: UserAuthLocalDataSource
) : UserAuthRepository {


    /**
     * Remote
     */

    override suspend fun getUserAuthDataExisted(
        type: String,
        data: String
    ): Result<UserAuthDataExisted> {
        return runCatching {
            remote.getUserAuthDataExisted(type, data).toDomain()
        }
    }

    override suspend fun postSocialLogin(socialLoginRequest: SocialLoginRequest): Result<LoginResponse> {
        return runCatching {
            remote.postSocialLogin(socialLoginRequest.toData()).toDomain()
        }
    }

    override suspend fun postNormalLogin(normalLoginRequest: NormalLoginRequest): Result<LoginResponse> {
        return runCatching {
            remote.postNormalLogin(normalLoginRequest.toData()).toDomain()
        }
    }

    override suspend fun getLogoutRedirect(): Result<String> {
        return runCatching {
            remote.getLogoutRedirect()
        }
    }

    /**
     * Local
     */

    override suspend fun updateToken(accessToken: String): Result<Unit> {
        return runCatching {
            local.updateToken(accessToken)
        }
    }

    override suspend fun getToken(): Result<String> {
        return runCatching {
            local.getToken()
        }
    }

    override suspend fun updateId(id: Long): Result<Unit> {
        return runCatching {
            local.updateId(id)
        }
    }

    override suspend fun getId(): Result<Long> {
        return runCatching {
            local.getId()
        }
    }
}