package com.uliga.domain.model.accountBook.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookTransactionRequest(
    val id: Long,
    val category: String,
    val payment: String,
    val date: String,
    val account: String,
    val value: Long,
    val memo: String,
    val sharedAccountBook: List<Long>,
) : Parcelable, Serializable