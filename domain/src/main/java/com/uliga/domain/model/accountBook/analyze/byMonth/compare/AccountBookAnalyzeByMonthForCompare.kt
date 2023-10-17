package com.uliga.domain.model.accountBook.analyze.byMonth.compare

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeByMonthForCompare(
    val compare: List<AccountBookAnalyzeByMonthForCompareElement>
): Parcelable, Serializable