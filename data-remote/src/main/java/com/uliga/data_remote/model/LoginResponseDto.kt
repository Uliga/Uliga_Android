package com.uliga.data_remote.model

import com.google.gson.annotations.SerializedName
import com.uliga.data.model.LoginMemberInfoResponseData
import com.uliga.data.model.LoginResponseData
import com.uliga.data.model.LoginTokenInfoResponseData
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerializedName("멤버 정보")
    val memberInfo: LoginMemberInfoResponseDto,
    @SerializedName("토큰 정보")
    val tokenInfo: LoginTokenInfoResponseDto
)

@Serializable
data class LoginMemberInfoResponseDto(
    val id: Long,
    val privateAccountBookId: Long?,
    val userName: String,
    val nickName: String,
    val email: String
)

@Serializable
data class LoginTokenInfoResponseDto(
    val accessToken: String,
    val grantType: String,
    val accessTokenExpiresIn: Long,
)

fun LoginResponseDto.toData() = LoginResponseData(
    memberInfo = memberInfo.toData(),
    tokenInfo = tokenInfo.toData()
)

fun LoginResponseData.toDto() = LoginResponseDto(
    memberInfo = memberInfo.toDto(),
    tokenInfo = tokenInfo.toDto()
)

fun LoginMemberInfoResponseDto.toData() = LoginMemberInfoResponseData(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)

fun LoginMemberInfoResponseData.toDto() = LoginMemberInfoResponseDto(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)

fun LoginTokenInfoResponseDto.toData() = LoginTokenInfoResponseData(
    accessToken = accessToken,
    grantType = grantType,
    accessTokenExpiresIn = accessTokenExpiresIn
)

fun LoginTokenInfoResponseData.toDto() = LoginTokenInfoResponseDto(
    accessToken = accessToken,
    grantType = grantType,
    accessTokenExpiresIn = accessTokenExpiresIn
)