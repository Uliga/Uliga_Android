package com.uliga.domain.model.accountBook.invitation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookInvitationReply(
    val id: Long,
    val memberName: String,
    val accountBookName: String,
    val join: Boolean
) : Parcelable, Serializable