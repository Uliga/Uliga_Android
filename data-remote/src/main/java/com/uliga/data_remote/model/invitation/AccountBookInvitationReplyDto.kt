package com.uliga.data_remote.model.invitation

import com.uliga.data.model.accountBook.invitation.AccountBookInvitationReplyData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookInvitationReplyDto(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
    val join: Boolean
)

fun AccountBookInvitationReplyData.toDto() = AccountBookInvitationReplyDto(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName,
    join = join
)

fun AccountBookInvitationReplyDto.toData() = AccountBookInvitationReplyData(
    id = id,
    memberName = memberName,
    accountBookName = accountBookName,
    join = join
)