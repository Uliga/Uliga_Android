package com.uliga.data_remote.model.accountBook.analyze.byWeek

import com.uliga.data.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeekData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByWeekDto(
    val weeklySums: List<AccountBookAnalyzeWeeklySumDto>,
    val sum: Long
)

fun AccountBookAnalyzeRecordByWeekData.toDto() = AccountBookAnalyzeRecordByWeekDto(
    weeklySums = weeklySums.map { it.toDto() },
    sum = sum
)

fun AccountBookAnalyzeRecordByWeekDto.toData() = AccountBookAnalyzeRecordByWeekData(
    weeklySums = weeklySums.map { it.toData() },
    sum = sum
)

