package com.uliga.data

import com.uliga.data.model.UserAuthDataExistedData

interface UserAuthRemoteDataSource {

    suspend fun getUserAuthDataExisted(type: String, data: String): UserAuthDataExistedData
}