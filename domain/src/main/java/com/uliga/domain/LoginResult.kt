package com.uliga.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class LoginResult(
    val user: String
): Parcelable, Serializable