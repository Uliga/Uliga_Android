package com.uliga.data.model.accountBook.transaction

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRecordInfo
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionRecordInfoData(
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

fun AccountBookTransactionRecordInfo.toData() = AccountBookTransactionRecordInfoData(
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

fun AccountBookTransactionRecordInfoData.toDomain() = AccountBookTransactionRecordInfo(
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