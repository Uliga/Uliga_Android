package com.uliga.domain.repository

import com.uliga.domain.model.accountBook.AccountBooks

interface AccountBookRepository {

    suspend fun getAccountBooks(): Result<AccountBooks>

}