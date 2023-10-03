package com.uliga.data_remote.model.userAuth

import com.uliga.data.model.userAuth.SocialLoginRequestData
import kotlinx.serialization.Serializable

@Serializable
data class SocialLoginRequestDto(
    val email: String,
    val userName: String,
    val nickName: String,
    val loginType: String,
    val applicationPassword: String
)

fun SocialLoginRequestDto.toData() = SocialLoginRequestData(
    email = email,
    userName = userName,
    nickName = nickName,
    loginType = loginType,
    applicationPassword = applicationPassword
)

fun SocialLoginRequestData.toDto() = SocialLoginRequestDto(
    email = email,
    userName = userName,
    nickName = nickName,
    loginType = loginType,
    applicationPassword = applicationPassword
)