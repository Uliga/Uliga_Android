package com.uliga.data_remote.model.accountBook.analyze.byMonth.category

import com.uliga.data.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByMonthForCategoryDto(
    val categories: List<AccountBookAnalyzeRecordByMonthForCategoryElementDto>,
    val sum: Long
)

fun AccountBookAnalyzeRecordByMonthForCategoryData.toDto() = AccountBookAnalyzeRecordByMonthForCategoryDto(
    categories = categories.map { it.toDto() },
    sum = sum
)

fun AccountBookAnalyzeRecordByMonthForCategoryDto.toData() = AccountBookAnalyzeRecordByMonthForCategoryData(
    categories = categories.map { it.toData() },
    sum = sum
)