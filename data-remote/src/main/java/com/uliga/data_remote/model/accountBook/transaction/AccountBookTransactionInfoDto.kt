package com.uliga.data_remote.model.accountBook.transaction

import com.uliga.data.model.accountBook.transaction.AccountBookTransactionInfoData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionInfoDto(
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

fun AccountBookTransactionInfoData.toDto() = AccountBookTransactionInfoDto(
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

fun AccountBookTransactionInfoDto.toData() = AccountBookTransactionInfoData(
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