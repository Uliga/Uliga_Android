package com.uliga.data.model.accountBook.analyze.byWeek

import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByWeekData(
    val weeklySums: List<AccountBookAnalyzeWeeklySumData>,
    val sum: Long
)

fun AccountBookAnalyzeRecordByWeek.toData() = AccountBookAnalyzeRecordByWeekData(
    weeklySums = weeklySums.map { it.toData() },
    sum = sum
)

fun AccountBookAnalyzeRecordByWeekData.toDomain() = AccountBookAnalyzeRecordByWeek(
    weeklySums = weeklySums.map { it.toDomain() },
    sum = sum
)