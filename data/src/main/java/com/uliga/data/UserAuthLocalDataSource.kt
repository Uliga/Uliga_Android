package com.uliga.data

interface UserAuthLocalDataSource {

    suspend fun updateToken(accessToken: String)

    suspend fun getToken(): String

    suspend fun updateId(id: Long)

    suspend fun getId(): Long
}