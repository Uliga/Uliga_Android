package com.uliga.data.model.accountBook.analyze.byMonth.category

import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryElement
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByMonthForCategoryElementData(
    val id: Long,
    val name: String,
    val value: Long
)

fun AccountBookAnalyzeRecordByMonthForCategoryElement.toData() = AccountBookAnalyzeRecordByMonthForCategoryElementData(
    id = id,
    name = name,
    value = value
)

fun AccountBookAnalyzeRecordByMonthForCategoryElementData.toDomain() = AccountBookAnalyzeRecordByMonthForCategoryElement(
    id = id,
    name = name,
    value = value
)
