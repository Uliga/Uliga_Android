package com.uliga.domain.repository

import com.uliga.domain.model.UserAuthEmailExisted

interface UserAuthRepository {

    suspend fun getUserAuthEmailExisted(email: String): Result<UserAuthEmailExisted>
}