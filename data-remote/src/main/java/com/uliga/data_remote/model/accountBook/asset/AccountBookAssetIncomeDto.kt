package com.uliga.data_remote.model.accountBook.asset

import com.uliga.data.model.accountBook.asset.AccountBookAssetIncomeData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetIncomeDto(
    val value: Long
)

fun AccountBookAssetIncomeData.toDto() = AccountBookAssetIncomeDto(
    value = value
)

fun AccountBookAssetIncomeDto.toData() = AccountBookAssetIncomeData(
    value = value
)