package com.uliga.data.model.accountBook.analyze.byMonth.compare

import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeByMonthForCompareData(
    val compare: List<AccountBookAnalyzeByMonthForCompareElementData>
)

fun AccountBookAnalyzeByMonthForCompare.toData() = AccountBookAnalyzeByMonthForCompareData(
    compare = compare.map { it.toData() }
)

fun AccountBookAnalyzeByMonthForCompareData.toDomain() = AccountBookAnalyzeByMonthForCompare(
    compare = compare.map { it.toDomain() }
)