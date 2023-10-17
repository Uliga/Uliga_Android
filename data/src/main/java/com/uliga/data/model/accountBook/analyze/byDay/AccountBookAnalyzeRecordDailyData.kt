package com.uliga.data.model.accountBook.analyze.byDay

import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordDaily
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordDailyData(
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeRecordDaily.toData() = AccountBookAnalyzeRecordDailyData(
    day = day,
    value = value
)

fun AccountBookAnalyzeRecordDailyData.toDomain() = AccountBookAnalyzeRecordDaily(
    day = day,
    value = value
)