package com.uliga.data.repository

import com.uliga.data.UserAuthLocalDataSource
import com.uliga.data.UserAuthRemoteDataSource
import com.uliga.data.model.toDomain
import com.uliga.domain.model.UserAuthDataExisted
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val userAuthRemoteDataSource: UserAuthRemoteDataSource,
    private val userAuthLocalDataSource: UserAuthLocalDataSource
): UserAuthRepository {
    override suspend fun getUserAuthDataExisted(type: String, data: String): Result<UserAuthDataExisted> {
        return runCatching {
            userAuthRemoteDataSource.getUserAuthDataExisted(type, data).toDomain()
        }
    }


}