package com.uliga.data.model.accountBook.analyze

import com.uliga.domain.model.accountBook.analyze.AccountBookAnalyzeRecord
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordData(
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeRecord.toData() = AccountBookAnalyzeRecordData(
    day = day,
    value = value
)

fun AccountBookAnalyzeRecordData.toDomain() = AccountBookAnalyzeRecord(
    day = day,
    value = value
)