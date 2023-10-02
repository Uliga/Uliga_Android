package com.uliga.data

import com.uliga.data.model.UserAuthEmailExistedData

interface UserAuthRemoteDataSource {

    suspend fun getUserAuthEmailExisted(email: String): UserAuthEmailExistedData
}