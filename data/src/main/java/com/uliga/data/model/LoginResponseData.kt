package com.uliga.data.model

import com.uliga.domain.model.LoginMemberInfoResponse
import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.LoginTokenInfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseData(
    val memberInfo: LoginMemberInfoResponseData,
    val tokenInfo: LoginTokenInfoResponseData
)

@Serializable
data class LoginMemberInfoResponseData(
    val id: Long,
    val privateAccountBookId: Long?,
    val userName: String,
    val nickName: String,
    val email: String
)

@Serializable
data class LoginTokenInfoResponseData(
    val accessToken: String,
    val grantType: String,
    val accessTokenExpiresIn: Long,
)

fun LoginMemberInfoResponseData.toDomain() = LoginMemberInfoResponse(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)

fun LoginResponseData.toDomain() = LoginResponse(
    memberInfo = memberInfo.toDomain(),
    tokenInfo = tokenInfo.toDomain()
)

fun LoginResponse.toData() = LoginResponseData(
    memberInfo = memberInfo.toData(),
    tokenInfo = tokenInfo.toData()
)

fun LoginMemberInfoResponse.toData() = LoginMemberInfoResponseData(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)

fun LoginTokenInfoResponseData.toDomain() = LoginTokenInfoResponse(
    accessToken = accessToken,
    grantType = grantType,
    accessTokenExpiresIn = accessTokenExpiresIn
)

fun LoginTokenInfoResponse.toData() = LoginTokenInfoResponseData(
    accessToken = accessToken,
    grantType = grantType,
    accessTokenExpiresIn = accessTokenExpiresIn
)