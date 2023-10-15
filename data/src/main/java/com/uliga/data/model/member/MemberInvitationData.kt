package com.uliga.data.model.member

import com.uliga.domain.model.member.MemberInvitation
import kotlinx.serialization.Serializable

@Serializable
data class MemberInvitationData(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
)

fun MemberInvitation.toData() = MemberInvitationData(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName
)

fun MemberInvitationData.toDomain() = MemberInvitation(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName
)