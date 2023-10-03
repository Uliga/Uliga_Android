package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBooks
import kotlinx.serialization.Serializable


@Serializable
data class AccountBooksData(
    val accountBooks: List<AccountBookData>
)

fun AccountBooksData.toDomain() = AccountBooks(
    accountBooks = accountBooks.map { it.toDomain() }
)

fun AccountBooks.toData() = AccountBooksData(
    accountBooks = accountBooks.map { it.toData() }
)