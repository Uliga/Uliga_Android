package com.uliga.data_remote.model.accountBook.analyze

import com.uliga.data.model.accountBook.analyze.AccountBookAnalyzeRecordByDayData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByDayDto(
    val records: List<AccountBookAnalyzeRecordDto>,
    val sum: Long,
    val diff: Long
)

fun AccountBookAnalyzeRecordByDayData.toDto() = AccountBookAnalyzeRecordByDayDto(
    records = records.map { it.toDto() },
    sum = sum,
    diff = diff
)

fun AccountBookAnalyzeRecordByDayDto.toData() = AccountBookAnalyzeRecordByDayData(
    records = records.map { it.toData() },
    sum = sum,
    diff = diff
)