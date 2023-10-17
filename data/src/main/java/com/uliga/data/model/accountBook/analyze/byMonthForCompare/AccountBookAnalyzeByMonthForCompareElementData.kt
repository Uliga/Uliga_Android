package com.uliga.data.model.accountBook.analyze.byMonthForCompare

import com.uliga.domain.model.accountBook.analyze.byMonthForCompare.AccountBookAnalyzeByMonthForCompareElement
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeByMonthForCompareElementData(
    val year: Long,
    val month: Long,
    val value: Long
)

fun AccountBookAnalyzeByMonthForCompareElement.toData() = AccountBookAnalyzeByMonthForCompareElementData(
    year = year,
    month = month,
    value = value
)

fun AccountBookAnalyzeByMonthForCompareElementData.toDomain() = AccountBookAnalyzeByMonthForCompareElement(
    year = year,
    month = month,
    value = value
)