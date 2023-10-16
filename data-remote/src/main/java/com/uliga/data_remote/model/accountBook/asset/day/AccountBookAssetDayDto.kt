package com.uliga.data_remote.model.accountBook.asset.day

import com.uliga.data.model.accountBook.asset.day.AccountBookAssetDayData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDayDto(
    val items: List<AccountBookAssetItemDto>
)

fun AccountBookAssetDayData.toDto() = AccountBookAssetDayDto(
    items = items.map { it.toDto() }
)

fun AccountBookAssetDayDto.toData() = AccountBookAssetDayData(
    items = items.map { it.toData() }
)
