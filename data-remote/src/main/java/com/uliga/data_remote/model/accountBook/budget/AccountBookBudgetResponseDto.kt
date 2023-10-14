package com.uliga.data_remote.model.accountBook.budget

import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookBudgetResponseDto(
    val id: Long,
    val year: Long,
    val month: Long,
    val value: Long,
)

fun AccountBookBudgetResponseData.toDto() = AccountBookBudgetResponseDto(
    id = id,
    year = year,
    month = month,
    value = value
)

fun AccountBookBudgetResponseDto.toData() = AccountBookBudgetResponseData(
    id = id,
    year = year,
    month = month,
    value = value
)