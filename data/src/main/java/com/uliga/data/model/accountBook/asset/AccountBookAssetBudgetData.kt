package com.uliga.data.model.accountBook.asset

import com.uliga.domain.model.accountBook.asset.AccountBookAssetBudget
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookAssetBudgetData(
    val value: Long
)

fun AccountBookAssetBudget.toData() = AccountBookAssetBudgetData(
    value = value
)

fun AccountBookAssetBudgetData.toDomain() = AccountBookAssetBudget(
    value = value
)