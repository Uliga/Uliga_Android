package com.uliga.data.model.accountBook.analyze.byDay

import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDay
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByDayData(
    val records: List<AccountBookAnalyzeRecordDailyData>,
    val sum: Long,
    val diff: Long?
)

fun AccountBookAnalyzeRecordByDay.toData() = AccountBookAnalyzeRecordByDayData(
    records = records.map { it.toData() },
    sum = sum,
    diff = diff
)

fun AccountBookAnalyzeRecordByDayData.toDomain() = AccountBookAnalyzeRecordByDay(
    records = records.map { it.toDomain() },
    sum = sum,
    diff = diff
)