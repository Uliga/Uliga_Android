package com.uliga.data.model.accountBook.transaction

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionRequestData(
    val id: Long,
    val category: String,
    val payment: String,
    val date: String,
    val account: String,
    val value: Long,
    val memo: String,
    val sharedAccountBook: List<Long>,
)

fun AccountBookTransactionRequest.toData() = AccountBookTransactionRequestData(
    id = id,
    category = category,
    payment = payment,
    date = date,
    account = account,
    value = value,
    memo = memo,
    sharedAccountBook = sharedAccountBook
)

fun AccountBookTransactionRequestData.toDomain() = AccountBookTransactionRequest(
    id = id,
    category = category,
    payment = payment,
    date = date,
    account = account,
    value = value,
    memo = memo,
    sharedAccountBook = sharedAccountBook
)