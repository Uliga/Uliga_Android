package com.uliga.data.model.accountBook.budget

import com.uliga.domain.model.accountBook.budget.AccountBookBudgetResponse
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookBudgetResponseData(
    val id: Long,
    val year: Long,
    val month: Long,
    val value: Long,
)

fun AccountBookBudgetResponse.toData() = AccountBookBudgetResponseData(
    id = id,
    year = year,
    month = month,
    value = value
)

fun AccountBookBudgetResponseData.toDomain() = AccountBookBudgetResponse(
    id = id,
    year = year,
    month = month,
    value = value
)