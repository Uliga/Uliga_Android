package com.uliga.domain.model.accountBook.analyze.byWeek

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookAnalyzeWeeklySum(
    val startDay: Long,
    val endDay: Long,
    val value: Long
) : Parcelable, Serializable