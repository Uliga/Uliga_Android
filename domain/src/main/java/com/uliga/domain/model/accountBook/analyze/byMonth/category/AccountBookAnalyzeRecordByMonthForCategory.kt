package com.uliga.domain.model.accountBook.analyze.byMonth.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeRecordByMonthForCategory(
    val categories: List<AccountBookAnalyzeRecordByMonthForCategoryElement>,
    val sum: Long,
): Parcelable, Serializable