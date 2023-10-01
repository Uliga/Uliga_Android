package com.uliga.domain

interface SocialLoginProvider {

    suspend fun login(type: AuthType): SocialLoginResult

    suspend fun logout(type: AuthType)
}