package com.uliga.data_remote.model.accountBook.asset

import com.uliga.data.model.accountBook.asset.AccountBookAssetBudgetData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookAssetBudgetDto(
    val value: Long
)

fun AccountBookAssetBudgetData.toDto() = AccountBookAssetBudgetDto(
    value = value
)

fun AccountBookAssetBudgetDto.toData() = AccountBookAssetBudgetData(
    value = value
)