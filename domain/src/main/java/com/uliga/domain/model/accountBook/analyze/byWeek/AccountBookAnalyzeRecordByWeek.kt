package com.uliga.domain.model.accountBook.analyze.byWeek

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookAnalyzeRecordByWeek(
    val weeklySums: List<AccountBookAnalyzeWeeklySum>,
    val sum: Long
) : Parcelable, Serializable