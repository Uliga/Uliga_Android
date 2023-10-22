package com.uliga.domain.model.accountBook.analyze.byMonth.schedule

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeFixedRecordByMonth(
    val schedules: List<AccountBookAnalyzeFixedRecordByMonthElement>,
    val sum: Long?
) : Parcelable, Serializable