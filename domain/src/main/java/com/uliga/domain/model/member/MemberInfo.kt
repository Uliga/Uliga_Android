package com.uliga.domain.model.member

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class MemberInfo(
    val id: Long,
    val privateAccountBookId: Long,
    val userName: String,
    val nickName: String,
    val email: String,
) : Parcelable, Serializable