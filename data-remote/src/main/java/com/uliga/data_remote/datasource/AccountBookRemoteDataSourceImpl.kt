package com.uliga.data_remote.datasource

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDayData
import com.uliga.data.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryData
import com.uliga.data.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompareData
import com.uliga.data.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeekData
import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import com.uliga.data.model.accountBook.asset.day.AccountBookAssetDayData
import com.uliga.data.model.accountBook.asset.month.AccountBookAssetMonthData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data.model.accountBook.invitation.AccountBookInvitationReplyData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionRequestData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionResponseData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.accountBook.AccountBookGenerationResponseDto
import com.uliga.data_remote.model.accountBook.AccountBooksDto
import com.uliga.data_remote.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDayDto
import com.uliga.data_remote.model.accountBook.analyze.byDay.toData
import com.uliga.data_remote.model.accountBook.analyze.byMonth.AccountBookAnalyzeRecordByMonthForCategoryDto
import com.uliga.data_remote.model.accountBook.analyze.byMonth.toData
import com.uliga.data_remote.model.accountBook.analyze.byMonthForCompare.AccountBookAnalyzeByMonthForCompareDto
import com.uliga.data_remote.model.accountBook.analyze.byMonthForCompare.toData
import com.uliga.data_remote.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeekDto
import com.uliga.data_remote.model.accountBook.analyze.byWeek.toData
import com.uliga.data_remote.model.accountBook.asset.AccountBookAssetDto
import com.uliga.data_remote.model.accountBook.asset.day.AccountBookAssetDayDto
import com.uliga.data_remote.model.accountBook.asset.day.toData
import com.uliga.data_remote.model.accountBook.asset.month.AccountBookAssetMonthDto
import com.uliga.data_remote.model.accountBook.asset.month.toData
import com.uliga.data_remote.model.accountBook.asset.toData
import com.uliga.data_remote.model.accountBook.budget.AccountBookBudgetResponseDto
import com.uliga.data_remote.model.accountBook.budget.toData
import com.uliga.data_remote.model.accountBook.budget.toDto
import com.uliga.data_remote.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseDto
import com.uliga.data_remote.model.accountBook.financeSchedule.toData
import com.uliga.data_remote.model.accountBook.financeSchedule.toDto
import com.uliga.data_remote.model.accountBook.toData
import com.uliga.data_remote.model.accountBook.toDto
import com.uliga.data_remote.model.accountBook.transaction.AccountBookTransactionResponseDto
import com.uliga.data_remote.model.accountBook.transaction.toData
import com.uliga.data_remote.model.accountBook.transaction.toDto
import com.uliga.data_remote.model.invitation.AccountBookInvitationReplyDto
import com.uliga.data_remote.model.invitation.toData
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
            url.path(
                Path.ACCOUNT_BOOK,
                accountBookId.toString(),
                Path.ASSET,
                year.toString(),
                month.toString()
            )
        }.body<AccountBookAssetDto>().toData()
    }

    override suspend fun postAccountBookInvitationReply(accountBookInvitationReplyData: AccountBookInvitationReplyData): AccountBookInvitationReplyData {
        return client.post {
            url.path(Path.ACCOUNT_BOOK, Path.INVITATION, Path.REPLY)
        }.body<AccountBookInvitationReplyDto>().toData()
    }

    override suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequestData: AccountBookTransactionRequestData
    ): AccountBookTransactionResponseData {
        return client.post {
            url.path(Path.ACCOUNT_BOOK, if (transactionType == "수입") Path.INCOME else Path.RECORD)
            setBody(accountBookTransactionRequestData.toDto())
        }.body<AccountBookTransactionResponseDto>().toData()
    }

    override suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAssetMonthData {
        return client.get {
            url.path(Path.ACCOUNT_BOOK, id.toString(), Path.ITEM, year.toString(), month.toString())
        }.body<AccountBookAssetMonthDto>().toData()
    }

    override suspend fun getAccountBookDayTransaction(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): AccountBookAssetDayData {
        return client.get {
            url.path(
                Path.ACCOUNT_BOOK,
                id.toString(),
                Path.ITEM,
                year.toString(),
                month.toString(),
                day.toString()
            )
        }.body<AccountBookAssetDayDto>().toData()
    }

    override suspend fun getAccountBookRecordByDay(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeRecordByDayData {
        return client.get {
            url.path(
                Path.ACCOUNT_BOOK,
                id.toString(),
                Path.ANALYZE,
                year.toString(),
                month.toString()
            )
        }.body<AccountBookAnalyzeRecordByDayDto>().toData()
    }

    override suspend fun getAccountBookRecordByWeek(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): AccountBookAnalyzeRecordByWeekData {
        return client.get {
            url.path(
                Path.ACCOUNT_BOOK,
                id.toString(),
                Path.ANALYZE,
                Path.WEEKLY,
                year.toString(),
                month.toString(),
                startDay.toString()
            )
        }.body<AccountBookAnalyzeRecordByWeekDto>().toData()
    }

    override suspend fun getAccountBookRecordByMonthForCompare(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeByMonthForCompareData {
        return client.get {
            url.path(
                Path.ACCOUNT_BOOK,
                id.toString(),
                Path.ANALYZE,
                Path.COMPARE,
                year.toString(),
                month.toString()
            )
        }.body<AccountBookAnalyzeByMonthForCompareDto>().toData()

    }

    override suspend fun getAccountBookRecordByMonthForCategory(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeRecordByMonthForCategoryData {
        return client.get {
            url.path(
                Path.ACCOUNT_BOOK,
                id.toString(),
                Path.ANALYZE,
                Path.CATEGORY,
                year.toString(),
                month.toString()
            )
        }.body<AccountBookAnalyzeRecordByMonthForCategoryDto>().toData()
    }


}