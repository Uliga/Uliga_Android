package com.uliga.data.model.accountBook.asset.month

import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetDayIncome
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDayIncomeData(
    val day: Long,
    val value: Long
)

fun AccountBookAssetDayIncome.toData() = AccountBookAssetDayIncomeData(
    day = day,
    value = value
)

fun AccountBookAssetDayIncomeData.toDomain() = AccountBookAssetDayIncome(
    day = day,
    value = value
)
