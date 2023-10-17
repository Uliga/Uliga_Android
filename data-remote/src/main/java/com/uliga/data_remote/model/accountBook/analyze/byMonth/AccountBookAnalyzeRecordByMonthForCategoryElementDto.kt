package com.uliga.data_remote.model.accountBook.analyze.byMonth

import com.uliga.data.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryElementData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAnalyzeRecordByMonthForCategoryElementDto(
    val id: Long,
    val name: String,
    val value: Long
)

fun AccountBookAnalyzeRecordByMonthForCategoryElementData.toDto() = AccountBookAnalyzeRecordByMonthForCategoryElementDto(
    id = id,
    name = name,
    value = value
)

fun AccountBookAnalyzeRecordByMonthForCategoryElementDto.toData() = AccountBookAnalyzeRecordByMonthForCategoryElementData(
    id = id,
    name = name,
    value = value
)