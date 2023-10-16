package com.uliga.data_remote.model.accountBook.asset.month

import com.uliga.data.model.accountBook.asset.month.AccountBookAssetDayRecordData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookAssetDayRecordDto(
    val day: Long,
    val value: Long
)

fun AccountBookAssetDayRecordData.toDto() = AccountBookAssetDayRecordDto(
    day = day,
    value = value
)

fun AccountBookAssetDayRecordDto.toData() = AccountBookAssetDayRecordData(
    day = day,
    value = value
)