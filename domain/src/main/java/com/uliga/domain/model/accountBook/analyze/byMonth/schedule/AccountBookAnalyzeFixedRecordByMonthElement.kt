package com.uliga.domain.model.accountBook.analyze.byMonth.schedule

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeFixedRecordByMonthElement(
    val name: String,
    val day: Long,
    val value: Long
): Parcelable, Serializable