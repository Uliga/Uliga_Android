package com.uliga.data_remote.model.accountBook.analyze.byMonth.schedule

import com.uliga.data.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonthData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeFixedRecordByMonthDto(
    val schedules: List<AccountBookAnalyzeFixedRecordByMonthElementDto>,
    val sum: Long?
)

fun AccountBookAnalyzeFixedRecordByMonthData.toDto() = AccountBookAnalyzeFixedRecordByMonthDto(
    schedules = schedules.map { it.toDto() },
    sum = sum
)

fun AccountBookAnalyzeFixedRecordByMonthDto.toData() = AccountBookAnalyzeFixedRecordByMonthData(
    schedules = schedules.map { it.toData() },
    sum = sum
)
