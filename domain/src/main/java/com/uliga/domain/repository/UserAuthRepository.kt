package com.uliga.domain.repository

import com.uliga.domain.model.UserAuthDataExisted

interface UserAuthRepository {

    suspend fun getUserAuthDataExisted(type: String, data: String): Result<UserAuthDataExisted>
}