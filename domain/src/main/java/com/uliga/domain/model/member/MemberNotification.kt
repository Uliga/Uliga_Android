package com.uliga.domain.model.member

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class MemberNotification(
    val scheduleName: String,
    val creatorName: String,
    val value: Long,
    val day: Long,
) : Parcelable, Serializable
