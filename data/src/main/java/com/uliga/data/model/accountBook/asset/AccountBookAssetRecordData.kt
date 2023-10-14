package com.uliga.data.model.accountBook.asset

import com.uliga.domain.model.accountBook.asset.AccountBookAssetRecord
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetRecordData(
    val value: Long
)

fun AccountBookAssetRecord.toData() = AccountBookAssetRecordData(
    value = value
)

fun AccountBookAssetRecordData.toDomain() = AccountBookAssetRecord(
    value = value
)