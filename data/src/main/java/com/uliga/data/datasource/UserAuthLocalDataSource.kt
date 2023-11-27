package com.uliga.data.datasource

interface UserAuthLocalDataSource {

    suspend fun updateToken(accessToken: String)

    suspend fun getToken(): String

    suspend fun updateId(id: Long)

    suspend fun getId(): Long

    suspend fun updateIsLogin(isLogin: Boolean)

    suspend fun getIsLogin(): Boolean
}