package com.uliga.data_remote.model.accountBook.asset.day

import com.uliga.data.model.accountBook.asset.day.AccountBookAssetDayIncomeData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDayIncomeDto(
    val day: Long,
    val value: Long
)

fun AccountBookAssetDayIncomeData.toDto() = AccountBookAssetDayIncomeDto(
    day = day,
    value = value
)

fun AccountBookAssetDayIncomeDto.toData() = AccountBookAssetDayIncomeData(
    day = day,
    value = value
)