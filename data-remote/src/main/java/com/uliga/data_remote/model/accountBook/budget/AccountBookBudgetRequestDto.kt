package com.uliga.data_remote.model.accountBook.budget

import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookBudgetRequestDto(
    val id: Long,
    val year: Long,
    val month: Long,
    val value: Long,
    val category: String?,
)

fun AccountBookBudgetRequestData.toDto() = AccountBookBudgetRequestDto(
    id = id,
    year = year,
    month = month,
    value = value,
    category = category
)

fun AccountBookBudgetRequestDto.toData() = AccountBookBudgetRequestData(
    id = id,
    year = year,
    month = month,
    value = value,
    category = category
)