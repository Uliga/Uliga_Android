package com.uliga.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class UserAuthDataExisted(
    val exists: Boolean?
): Parcelable, Serializable