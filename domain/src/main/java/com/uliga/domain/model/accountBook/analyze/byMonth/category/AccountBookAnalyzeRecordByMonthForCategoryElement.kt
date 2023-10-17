package com.uliga.domain.model.accountBook.analyze.byMonth.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeRecordByMonthForCategoryElement(
    val id: Long,
    val name: String,
    val value: Long
): Parcelable, Serializable