package com.uliga.domain

interface SocialLoginProvider {

    suspend fun login(
        type: AuthType,
        checkedIdToken: String?,
        checkedEmail: String?,
        checkedName: String?
    ): SocialLoginResult

    suspend fun logout(type: AuthType)
}