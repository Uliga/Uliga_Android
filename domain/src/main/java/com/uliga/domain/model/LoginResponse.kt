package com.uliga.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class LoginResponse(
    val memberInfo: LoginMemberInfoResponse,
    val tokenInfo: LoginTokenInfoResponse
) : Parcelable, Serializable

@Parcelize
data class LoginMemberInfoResponse(
    val id: Long,
    val privateAccountBookId: Long?,
    val userName: String,
    val nickName: String,
    val email: String
) : Parcelable, Serializable

@Parcelize
data class LoginTokenInfoResponse(
    val accessToken: String,
    val grantType: String,
    val accessTokenExpiresIn: Long,
) : Parcelable, Serializable