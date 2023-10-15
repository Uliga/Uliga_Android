package com.uliga.data_remote.model.accountBook.transaction

import com.uliga.data.model.accountBook.transaction.AccountBookTransactionRequestData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionRequestDto(
    val id: Long,
    val category: String,
    val payment: String,
    val date: String,
    val account: String,
    val value: Long,
    val memo: String,
    val sharedAccountBook: List<Long>,
)

fun AccountBookTransactionRequestData.toDto() = AccountBookTransactionRequestDto(
    id = id,
    category = category,
    payment = payment,
    date = date,
    account = account,
    value = value,
    memo = memo,
    sharedAccountBook = sharedAccountBook
)

fun AccountBookTransactionRequestDto.toData() = AccountBookTransactionRequestData(
    id = id,
    category = category,
    payment = payment,
    date = date,
    account = account,
    value = value,
    memo = memo,
    sharedAccountBook = sharedAccountBook
)