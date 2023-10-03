package com.uliga.data.datasource

import com.uliga.data.model.accountBook.AccountBooksData

interface AccountBookRemoteDataSource {

    suspend fun getAccountBooks(): AccountBooksData

}