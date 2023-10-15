package com.uliga.data.model.member

import com.uliga.domain.model.member.Member
import kotlinx.serialization.Serializable

@Serializable
data class MemberData(
    val memberInfo: MemberInfoData,
    val invitations: List<MemberInvitationData>,
    val notifications: List<MemberNotificationData>
)

fun Member.toData() = MemberData(
    memberInfo = memberInfo.toData(),
    invitations = invitations.map { it.toData() },
    notifications = notifications.map { it.toData() }
)

fun MemberData.toDomain() = Member(
    memberInfo = memberInfo.toDomain(),
    invitations = invitations.map { it.toDomain() },
    notifications = notifications.map { it.toDomain() }
)