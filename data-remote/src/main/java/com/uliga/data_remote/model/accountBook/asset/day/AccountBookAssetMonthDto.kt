package com.uliga.data_remote.model.accountBook.asset.day

import com.uliga.data.model.accountBook.asset.day.AccountBookAssetMonthData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetMonthDto(
    val incomes: List<AccountBookAssetDayIncomeDto>,
    val records: List<AccountBookAssetDayRecordDto>,
)

fun AccountBookAssetMonthData.toDto() = AccountBookAssetMonthDto(
    incomes = incomes.map { it.toDto() },
    records = records.map { it.toDto() }
)

fun AccountBookAssetMonthDto.toData() = AccountBookAssetMonthData(
    incomes = incomes.map { it.toData() },
    records = records.map { it.toData() }
)
