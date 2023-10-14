package com.uliga.data_remote.datasource

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.accountBook.AccountBookGenerationResponseDto
import com.uliga.data_remote.model.accountBook.AccountBooksDto
import com.uliga.data_remote.model.accountBook.asset.AccountBookAssetDto
import com.uliga.data_remote.model.accountBook.asset.toData
import com.uliga.data_remote.model.accountBook.budget.AccountBookBudgetResponseDto
import com.uliga.data_remote.model.accountBook.budget.toData
import com.uliga.data_remote.model.accountBook.budget.toDto
import com.uliga.data_remote.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseDto
import com.uliga.data_remote.model.accountBook.financeSchedule.toData
import com.uliga.data_remote.model.accountBook.financeSchedule.toDto
import com.uliga.data_remote.model.accountBook.toData
import com.uliga.data_remote.model.accountBook.toDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
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

    override suspend fun postAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData {
        return client.post {
            url.path(Path.ACCOUNT_BOOK, Path.BUDGET)
            setBody(accountBookBudgetRequestData.toDto())
        }.body<AccountBookBudgetResponseDto>().toData()
    }

    override suspend fun patchAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData {
        return client.patch {
            url.path(Path.BUDGET)
            setBody(accountBookBudgetRequestData.toDto())
        }.body<AccountBookBudgetResponseDto>().toData()
    }

    override suspend fun getAccountBookMonthAsset(
        accountBookId: Long,
        year: Int,
        month: Int
    ): AccountBookAssetData {
        return client.get {
            url.path(Path.ACCOUNT_BOOK, accountBookId.toString(), Path.ASSET, year.toString(), month.toString())
        }.body<AccountBookAssetDto>().toData()
    }


}