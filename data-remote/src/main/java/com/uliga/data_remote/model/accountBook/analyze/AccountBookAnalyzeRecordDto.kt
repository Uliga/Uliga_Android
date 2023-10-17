package com.uliga.data_remote.model.accountBook.analyze

import com.uliga.data.model.accountBook.analyze.AccountBookAnalyzeRecordData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordDto(
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeRecordData.toDto() = AccountBookAnalyzeRecordDto(
    day = day,
    value = value
)

fun AccountBookAnalyzeRecordDto.toData() = AccountBookAnalyzeRecordData(
    day = day,
    value = value
)