package com.uliga.data.model.accountBook.asset

import com.uliga.domain.model.accountBook.asset.AccountBookAssetBudget
import com.uliga.domain.model.accountBook.asset.AccountBookAssetIncome
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetIncomeData(
    val value: Long
)

fun AccountBookAssetIncome.toData() = AccountBookAssetIncomeData(
    value = value
)

fun AccountBookAssetIncomeData.toDomain() = AccountBookAssetIncome(
    value = value
)