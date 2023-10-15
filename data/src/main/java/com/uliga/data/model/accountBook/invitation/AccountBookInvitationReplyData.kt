package com.uliga.data.model.accountBook.invitation

import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookInvitationReplyData(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
    val join: Boolean
)

fun AccountBookInvitationReply.toData() = AccountBookInvitationReplyData(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName,
    join = join
)

fun AccountBookInvitationReplyData.toDomain() = AccountBookInvitationReply(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName,
    join = join
)