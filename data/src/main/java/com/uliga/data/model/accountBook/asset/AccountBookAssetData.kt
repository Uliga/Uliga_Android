package com.uliga.data.model.accountBook.asset

import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetData(
    val income: AccountBookAssetIncomeData,
    val record: AccountBookAssetRecordData,
    val budget: AccountBookAssetBudgetData,
)

fun AccountBookAsset.toData() = AccountBookAssetData(
    income = income.toData(),
    record = record.toData(),
    budget = budget.toData()
)

fun AccountBookAssetData.toDomain() = AccountBookAsset(
    income = income.toDomain(),
    record = record.toDomain(),
    budget = budget.toDomain()
)