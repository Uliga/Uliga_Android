package com.uliga.data.model.accountBook.analyze.byMonth.category

import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategory
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByMonthForCategoryData(
    val categories: List<AccountBookAnalyzeRecordByMonthForCategoryElementData>,
    val sum: Long
)

fun AccountBookAnalyzeRecordByMonthForCategory.toData() = AccountBookAnalyzeRecordByMonthForCategoryData(
    categories = categories.map { it.toData() },
    sum = sum
)

fun AccountBookAnalyzeRecordByMonthForCategoryData.toDomain() = AccountBookAnalyzeRecordByMonthForCategory(
    categories = categories.map { it.toDomain() },
    sum = sum
)