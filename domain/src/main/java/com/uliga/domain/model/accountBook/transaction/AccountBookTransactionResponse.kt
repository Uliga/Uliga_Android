package com.uliga.domain.model.accountBook.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookTransactionResponse(
    val accountBookId: Long,
    val recordInfo: AccountBookTransactionInfo?,
    val incomeInfo: AccountBookTransactionInfo?
) : Parcelable, Serializable