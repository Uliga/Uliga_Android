package com.uliga.data.model.accountBook.analyze.byMonthForCompare

import com.uliga.domain.model.accountBook.analyze.byMonthForCompare.AccountBookAnalyzeByMonthForCompare
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