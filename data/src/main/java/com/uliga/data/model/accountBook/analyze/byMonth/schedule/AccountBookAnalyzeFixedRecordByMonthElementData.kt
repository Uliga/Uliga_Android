package com.uliga.data.model.accountBook.analyze.byMonth.schedule

import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonthElement
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeFixedRecordByMonthElementData(
    val name: String,
    val day: Long,
    val value: Long
)

fun AccountBookAnalyzeFixedRecordByMonthElement.toData() = AccountBookAnalyzeFixedRecordByMonthElementData(
    name = name,
    day = day,
    value = value
)

fun AccountBookAnalyzeFixedRecordByMonthElementData.toDomain() = AccountBookAnalyzeFixedRecordByMonthElement(
    name = name,
    day = day,
    value = value
)
