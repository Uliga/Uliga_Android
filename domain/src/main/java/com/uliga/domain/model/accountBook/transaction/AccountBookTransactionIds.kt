package com.uliga.domain.model.accountBook.transaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookTransactionIds(
    val ids: List<Long>
) : Parcelable, Serializable