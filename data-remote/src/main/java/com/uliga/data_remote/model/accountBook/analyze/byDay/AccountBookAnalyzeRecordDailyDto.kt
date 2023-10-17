package com.uliga.data_remote.model.accountBook.analyze.byDay

import com.uliga.data.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordDailyData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordDailyDto(
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeRecordDailyData.toDto() = AccountBookAnalyzeRecordDailyDto(
    day = day,
    value = value
)

fun AccountBookAnalyzeRecordDailyDto.toData() = AccountBookAnalyzeRecordDailyData(
    day = day,
    value = value
)