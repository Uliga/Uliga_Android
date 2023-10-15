package com.uliga.data_remote.model.accountBook.transaction

import com.uliga.data.model.accountBook.transaction.AccountBookTransactionRecordInfoData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionRecordInfoDto(
    val id: Long,
    val value: Long,
    val payment: String,
    val account: String,
    val memo: String,
    val year: Long,
    val month: Long,
    val day: Long,
    val creator: String,
    val avatarUrl: String,
    val category: String
)

fun AccountBookTransactionRecordInfoData.toDto() = AccountBookTransactionRecordInfoDto(
    id = id,
    value = value,
    payment = payment,
    account = account,
    memo = memo,
    year = year,
    month = month,
    day = day,
    creator = creator,
    avatarUrl = avatarUrl,
    category = category
)

fun AccountBookTransactionRecordInfoDto.toData() = AccountBookTransactionRecordInfoData(
    id = id,
    value = value,
    payment = payment,
    account = account,
    memo = memo,
    year = year,
    month = month,
    day = day,
    creator = creator,
    avatarUrl = avatarUrl,
    category = category
)