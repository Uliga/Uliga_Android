package com.uliga.data_remote.datasource

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.accountBook.AccountBookGenerationResponseDto
import com.uliga.data_remote.model.accountBook.AccountBooksDto
import com.uliga.data_remote.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseDto
import com.uliga.data_remote.model.accountBook.financeSchedule.toData
import com.uliga.data_remote.model.accountBook.financeSchedule.toDto
import com.uliga.data_remote.model.accountBook.toData
import com.uliga.data_remote.model.accountBook.toDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
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

    override suspend fun postAccountBook(accountBookGenerationRequestData: AccountBookGenerationRequestData): AccountBookGenerationResponseData {
        return client.post {
            url.path(Path.ACCOUNT_BOOK)
            setBody(accountBookGenerationRequestData.toDto())
        }.body<AccountBookGenerationResponseDto>().toData()
    }

    override suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequestData: AccountBookFinanceScheduleRequestData): AccountBookFinanceScheduleResponseData {
        return client.post {
            url.path(Path.ACCOUNT_BOOK, Path.SCHEDULE)
            setBody(accountBookFinanceScheduleRequestData.toDto())
        }.body<AccountBookFinanceScheduleResponseDto>().toData()
    }


}