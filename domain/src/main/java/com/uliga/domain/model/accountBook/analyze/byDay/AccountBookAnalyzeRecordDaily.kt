package com.uliga.domain.model.accountBook.analyze.byDay

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeRecordDaily(
    val day: Long,
    val value: Long
) : Parcelable, Serializable