package com.uliga.data.model.accountBook.budget

import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookBudgetRequestData(
    val id: Long,
    val year: Long,
    val month: Long,
    val value: Long,
    val category: String?,
)


fun AccountBookBudgetRequest.toData() = AccountBookBudgetRequestData(
    id = id,
    year = year,
    month = month,
    value = value,
    category = category
)

fun AccountBookBudgetRequestData.toDomain() = AccountBookBudgetRequest(
    id = id,
    year = year,
    month = month,
    value = value,
    category = category
)