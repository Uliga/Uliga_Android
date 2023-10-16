package com.uliga.data.model.accountBook.asset.day

import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetDay
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookAssetDayData(
    val items: List<AccountBookAssetItemData>
)

fun AccountBookAssetDay.toData() = AccountBookAssetDayData(
    items = items.map { it.toData() }
)

fun AccountBookAssetDayData.toDomain() = AccountBookAssetDay(
    items = items.map { it.toDomain() }
)