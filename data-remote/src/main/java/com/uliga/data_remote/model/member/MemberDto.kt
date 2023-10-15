package com.uliga.data_remote.model.member

import com.uliga.data.model.member.MemberData
import kotlinx.serialization.Serializable

@Serializable
data class MemberDto(
    val memberInfo: MemberInfoDto,
    val invitations: List<MemberInvitationDto>,
    val notifications: List<MemberNotificationDto>
)

fun MemberData.toDto() = MemberDto(
    memberInfo = memberInfo.toDto(),
    invitations = invitations.map { it.toDto() },
    notifications = notifications.map { it.toDto() }
)

fun MemberDto.toData() = MemberData(
    memberInfo = memberInfo.toData(),
    invitations = invitations.map { it.toData() },
    notifications = notifications.map { it.toData() }
)