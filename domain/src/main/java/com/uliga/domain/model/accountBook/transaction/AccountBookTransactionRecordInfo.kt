package com.uliga.domain.model.accountBook.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookTransactionRecordInfo(
    val id: Long,
    val value: Long,
    val payment: String,
    val account: String,
    val memo: String,
    val year: Long,
    val month: Long,
    val day: Long,
    val creator: String,
    val avatarUrl: String,
    val category: String,
) : Parcelable, Serializable