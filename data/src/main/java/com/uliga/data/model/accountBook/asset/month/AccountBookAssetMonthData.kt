package com.uliga.data.model.accountBook.asset.month

import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetMonth
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetMonthData(
    val incomes: List<AccountBookAssetDayIncomeData>,
    val records: List<AccountBookAssetDayRecordData>,
)

fun AccountBookAssetMonth.toData() = AccountBookAssetMonthData(
    incomes = incomes.map { it.toData() },
    records = records.map { it.toData() }
)

fun AccountBookAssetMonthData.toDomain() = AccountBookAssetMonth(
    incomes = incomes.map { it.toDomain() },
    records = records.map { it.toDomain() }
)
