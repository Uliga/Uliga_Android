package com.uliga.data_remote.model.accountBook.asset

import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDto(
    val income: AccountBookAssetIncomeDto,
    val record: AccountBookAssetRecordDto,
    val budget: AccountBookAssetBudgetDto,
)

fun AccountBookAssetData.toDto() = AccountBookAssetDto(
    income = income.toDto(),
    record = record.toDto(),
    budget = budget.toDto()
)

fun AccountBookAssetDto.toData() = AccountBookAssetData(
    income = income.toData(),
    record = record.toData(),
    budget = budget.toData()
)
