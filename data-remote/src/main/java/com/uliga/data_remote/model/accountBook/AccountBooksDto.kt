package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBooksData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBooksDto(
    val accountBooks: List<AccountBookDto>
)

fun AccountBooksDto.toData() = AccountBooksData(
    accountBooks = accountBooks.map { it.toData() }
)

fun AccountBooksData.toDto() = AccountBooksDto(
    accountBooks = accountBooks.map { it.toDto() }
)