package com.uliga.data.model.accountBook.analyze.byWeek

import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeWeeklySum
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookAnalyzeWeeklySumData(
    val startDay: Long,
    val endDay: Long,
    val value: Long
)

fun AccountBookAnalyzeWeeklySum.toData() = AccountBookAnalyzeWeeklySumData(
    startDay = startDay,
    endDay = endDay,
    value = value
)

fun AccountBookAnalyzeWeeklySumData.toDomain() = AccountBookAnalyzeWeeklySum(
    startDay = startDay,
    endDay = endDay,
    value = value
)