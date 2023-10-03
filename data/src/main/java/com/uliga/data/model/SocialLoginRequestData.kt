package com.uliga.data.model

import com.uliga.domain.model.SocialLoginRequest
import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginRequestData(
    val email: String,
    val userName: String,
    val nickName: String,
    val loginType: String,
    val applicationPassword: String
)

fun SocialLoginRequestData.toDomain() = SocialLoginRequest(
    email = email,
    userName = userName,
    nickName = nickName,
    loginType = loginType,
    applicationPassword = applicationPassword
)

fun SocialLoginRequest.toData() = SocialLoginRequestData(
    email = email,
    userName = userName,
    nickName = nickName,
    loginType = loginType,
    applicationPassword = applicationPassword
)