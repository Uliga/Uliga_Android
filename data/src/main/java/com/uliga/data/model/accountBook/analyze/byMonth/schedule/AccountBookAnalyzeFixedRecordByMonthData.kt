package com.uliga.data.model.accountBook.analyze.byMonth.schedule

import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonth
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeFixedRecordByMonthData(
    val schedules: List<AccountBookAnalyzeFixedRecordByMonthElementData>,
    val sum: Long?
)

fun AccountBookAnalyzeFixedRecordByMonth.toData() = AccountBookAnalyzeFixedRecordByMonthData(
    schedules = schedules.map { it.toData() },
    sum = sum
)

fun AccountBookAnalyzeFixedRecordByMonthData.toDomain() = AccountBookAnalyzeFixedRecordByMonth(
    schedules = schedules.map { it.toDomain() },
    sum = sum
)
