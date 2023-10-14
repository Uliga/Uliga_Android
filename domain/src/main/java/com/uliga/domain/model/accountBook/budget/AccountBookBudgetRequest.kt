package com.uliga.domain.model.accountBook.budget

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookBudgetRequest(
    val id: Long,
    val year: Long,
    val month: Long,
    val value: Long,
    val category: String?,
) : Parcelable, Serializable