package com.uliga.data.repository

import com.uliga.data.UserAuthLocalDataSource
import com.uliga.data.UserAuthRemoteDataSource
import com.uliga.data.model.toData
import com.uliga.data.model.toDomain
import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.NormalLoginRequest
import com.uliga.domain.model.SocialLoginRequest
import com.uliga.domain.model.UserAuthDataExisted
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val userAuthRemoteDataSource: UserAuthRemoteDataSource,
    private val userAuthLocalDataSource: UserAuthLocalDataSource
) : UserAuthRepository {


    /**
     * Remote
     */

    override suspend fun getUserAuthDataExisted(
        type: String,
        data: String
    ): Result<UserAuthDataExisted> {
        return runCatching {
            userAuthRemoteDataSource.getUserAuthDataExisted(type, data).toDomain()
        }
    }

    override suspend fun postSocialLogin(socialLoginRequest: SocialLoginRequest): Result<LoginResponse> {
        return runCatching {
            userAuthRemoteDataSource.postSocialLogin(socialLoginRequest.toData()).toDomain()
        }
    }

    override suspend fun postNormalLogin(normalLoginRequest: NormalLoginRequest): Result<LoginResponse> {
        return runCatching {
            userAuthRemoteDataSource.postNormalLogin(normalLoginRequest.toData()).toDomain()
        }
    }

    /**
     * Local
     */

    override suspend fun updateToken(accessToken: String): Result<Unit> {
        return runCatching {
            userAuthLocalDataSource.updateToken(accessToken)
        }
    }

    override suspend fun getToken(): Result<String> {
        return runCatching {
            userAuthLocalDataSource.getToken()
        }
    }

    override suspend fun updateId(id: Long): Result<Unit> {
        return runCatching {
            userAuthLocalDataSource.updateId(id)
        }
    }

    override suspend fun getId(): Result<Long> {
        return runCatching {
            userAuthLocalDataSource.getId()
        }
    }
}