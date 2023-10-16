package com.uliga.data.model.accountBook.asset.day

import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetItem
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetItemData(
    val id: Long,
    val value: Long,
    val payment: String,
    val account: String,
    val memo: String,
    val year: Long,
    val month: Long,
    val day: Long,
    val type: String,
    val creator: String,
    val category: String,
    val avatarUrl: String
)

fun AccountBookAssetItem.toData() = AccountBookAssetItemData(
    id = id,
    value = value,
    payment = payment,
    account = account,
    memo = memo,
    year = year,
    month = month,
    day = day,
    type = type,
    creator = creator,
    category = category,
    avatarUrl = avatarUrl
)

fun AccountBookAssetItemData.toDomain() = AccountBookAssetItem(
    id = id,
    value = value,
    payment = payment,
    account = account,
    memo = memo,
    year = year,
    month = month,
    day = day,
    type = type,
    creator = creator,
    category = category,
    avatarUrl = avatarUrl
)