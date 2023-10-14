package com.uliga.data_remote.model.accountBook.asset

import com.uliga.data.model.accountBook.asset.AccountBookAssetRecordData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetRecordDto(
    val value: Long
)

fun AccountBookAssetRecordData.toDto() = AccountBookAssetRecordDto(
    value = value
)

fun AccountBookAssetRecordDto.toData() = AccountBookAssetRecordData(
    value = value
)