package com.uliga.data_remote.model.member

import com.uliga.data.model.member.MemberInvitationData
import kotlinx.serialization.Serializable

@Serializable
data class MemberInvitationDto(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
)

fun MemberInvitationData.toDto() = MemberInvitationDto(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName
)

fun MemberInvitationDto.toData() = MemberInvitationData(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName
)