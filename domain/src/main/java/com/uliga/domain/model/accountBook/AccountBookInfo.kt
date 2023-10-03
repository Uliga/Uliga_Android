package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookInfo(
    val accountBookId: Long,
    val isPrivate: Boolean,
    val accountBookName: String,
    val accountBookAuthority: String,
    val relationShip: String,
    val getNotification: Boolean,
    val avatarUrl: String,
) : Parcelable, Serializable