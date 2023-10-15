package com.uliga.domain.model.member

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class Member(
    val memberInfo: MemberInfo,
    val invitations: List<MemberInvitation>,
    val notifications: List<MemberNotification>,
): Parcelable, Serializable