package com.uliga.domain.model.accountBook.analyze.byDay

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookAnalyzeRecordByDay(
    val records: List<AccountBookAnalyzeRecordDaily>,
    val sum: Long,
    val diff: Long
) : Parcelable, Serializable

