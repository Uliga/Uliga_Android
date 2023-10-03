package com.uliga.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class NormalLoginRequest(
    val email: String,
    val password: String
) : Parcelable, Serializable