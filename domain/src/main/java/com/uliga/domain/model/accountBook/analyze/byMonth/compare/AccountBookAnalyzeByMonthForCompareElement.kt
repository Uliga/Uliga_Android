package com.uliga.domain.model.accountBook.analyze.byMonth.compare

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookAnalyzeByMonthForCompareElement(
    val year: Long,
    val month: Long,
    val value: Long
) : Parcelable, Serializable