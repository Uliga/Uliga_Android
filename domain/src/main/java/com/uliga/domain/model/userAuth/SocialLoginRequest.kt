package com.uliga.domain.model.userAuth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class SocialLoginRequest(
    val email: String,
    val userName: String,
    val nickName: String,
    val loginType: String,
    val applicationPassword: String
): Parcelable, Serializable