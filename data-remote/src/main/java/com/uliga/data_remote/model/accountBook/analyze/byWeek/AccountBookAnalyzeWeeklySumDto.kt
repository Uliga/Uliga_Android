package com.uliga.data_remote.model.accountBook.analyze.byWeek

import com.uliga.data.model.accountBook.analyze.byWeek.AccountBookAnalyzeWeeklySumData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeWeeklySumDto(
    val startDay: Long,
    val endDay: Long,
    val value: Long
)

fun AccountBookAnalyzeWeeklySumData.toDto() = AccountBookAnalyzeWeeklySumDto(
    startDay = startDay,
    endDay = endDay,
    value = value
)

fun AccountBookAnalyzeWeeklySumDto.toData() = AccountBookAnalyzeWeeklySumData(
    startDay = startDay,
    endDay = endDay,
    value = value
)