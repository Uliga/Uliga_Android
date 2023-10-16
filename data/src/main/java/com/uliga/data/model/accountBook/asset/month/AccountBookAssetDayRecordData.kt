package com.uliga.data.model.accountBook.asset.month

import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetDayRecord
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDayRecordData(
    val day: Long,
    val value: Long
)

fun AccountBookAssetDayRecord.toData() = AccountBookAssetDayRecordData(
    day = day,
    value = value
)

fun AccountBookAssetDayRecordData.toDomain() = AccountBookAssetDayRecord(
    day = day,
    value = value
)
