package com.uliga.data_remote.model.accountBook.analyze.byMonth.compare

import com.uliga.data.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompareData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeByMonthForCompareDto(
    val compare: List<AccountBookAnalyzeByMonthForCompareElementDto>
)

fun AccountBookAnalyzeByMonthForCompareData.toDto() = AccountBookAnalyzeByMonthForCompareDto(
    compare = compare.map { it.toDto() }
)

fun AccountBookAnalyzeByMonthForCompareDto.toData() = AccountBookAnalyzeByMonthForCompareData(
    compare = compare.map { it.toData() }
)
