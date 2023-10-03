package com.uliga.data_remote.datasource

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.accountBook.AccountBooksDto
import com.uliga.data_remote.model.accountBook.toData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

class AccountBookRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : AccountBookRemoteDataSource {
    override suspend fun getAccountBooks(): AccountBooksData {
        return client.get {
            url.path(Path.ACCOUNT_BOOK)
        }.body<AccountBooksDto>().toData()
    }


}