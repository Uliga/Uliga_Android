package com.uliga.data.model.accountBook.analyze

import com.uliga.domain.model.accountBook.analyze.AccountBookAnalyzeRecordByDay
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByDayData(
    val records: List<AccountBookAnalyzeRecordData>,
    val sum: Long,
    val diff: Long
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