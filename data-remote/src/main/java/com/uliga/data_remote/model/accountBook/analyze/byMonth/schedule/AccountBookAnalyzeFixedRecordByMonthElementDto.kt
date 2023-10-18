package com.uliga.data_remote.model.accountBook.analyze.byMonth.schedule

import com.uliga.data.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonthElementData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeFixedRecordByMonthElementDto(
    val name: String,
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeFixedRecordByMonthElementData.toDto() = AccountBookAnalyzeFixedRecordByMonthElementDto(
    name = name,
    day = day,
    value = value
)

fun AccountBookAnalyzeFixedRecordByMonthElementDto.toData() = AccountBookAnalyzeFixedRecordByMonthElementData(
    name = name,
    day = day,
    value = value
)