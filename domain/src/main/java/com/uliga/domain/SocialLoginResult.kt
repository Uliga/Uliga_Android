package com.uliga.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class SocialLoginResult(
    val type: AuthType,
    val token: String?,
    val email: String?,
    val name: String?
) : Parcelable, Serializable

enum class AuthType {
    KAKAO,
    GOOGLE
}