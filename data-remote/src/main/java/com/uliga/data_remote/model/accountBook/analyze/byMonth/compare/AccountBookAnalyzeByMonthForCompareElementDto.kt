package com.uliga.data_remote.model.accountBook.analyze.byMonth.compare

import com.uliga.data.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompareElementData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeByMonthForCompareElementDto(
    val year: Long,
    val month: Long,
    val value: Long
)

fun AccountBookAnalyzeByMonthForCompareElementData.toDto() = AccountBookAnalyzeByMonthForCompareElementDto(
    year = year,
    month = month,
    value = value
)

fun AccountBookAnalyzeByMonthForCompareElementDto.toData() = AccountBookAnalyzeByMonthForCompareElementData(
    year = year,
    month = month,
    value = value
)