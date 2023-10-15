package com.uliga.domain.model.member

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class MemberInvitation(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
) : Parcelable, Serializable