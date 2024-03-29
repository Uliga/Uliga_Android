package com.uliga.data.datasource

import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDayData
import com.uliga.data.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryData
import com.uliga.data.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompareData
import com.uliga.data.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonthData
import com.uliga.data.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeekData
import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import com.uliga.data.model.accountBook.asset.day.AccountBookAssetDayData
import com.uliga.data.model.accountBook.asset.month.AccountBookAssetMonthData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data.model.accountBook.invitation.AccountBookInvitationReplyData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionIdsData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionRequestData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionResponseData

interface AccountBookRemoteDataSource {

    suspend fun getAccountBooks(): AccountBooksData

    suspend fun postAccountBook(accountBookGenerationRequestData: AccountBookGenerationRequestData): AccountBookGenerationResponseData

    suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequestData: AccountBookFinanceScheduleRequestData): AccountBookFinanceScheduleResponseData

    suspend fun postAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData

    suspend fun patchAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData

    suspend fun getAccountBookMonthAsset(
        accountBookId: Long,
        year: Int,
        month: Int
    ): AccountBookAssetData

    suspend fun postAccountBookInvitationReply(accountBookInvitationReplyData: AccountBookInvitationReplyData): AccountBookInvitationReplyData

    suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequestData: AccountBookTransactionRequestData
    ): AccountBookTransactionResponseData

    suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAssetMonthData

    suspend fun getAccountBookDayTransaction(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): AccountBookAssetDayData

    suspend fun deleteAccountBookDayTransaction(
        accountBookTransactionIdsBookIds: AccountBookTransactionIdsData
    ): String

    /**
     * Analyze
     */

    suspend fun getAccountBookRecordByDay(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeRecordByDayData

    suspend fun getAccountBookRecordByWeek(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): AccountBookAnalyzeRecordByWeekData

    suspend fun getAccountBookRecordByMonthForCompare(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeByMonthForCompareData

    suspend fun getAccountBookRecordByMonthForCategory(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAnalyzeRecordByMonthForCategoryData

    suspend fun getAccountBookFixedRecordByMonth(
        id: Long
    ): AccountBookAnalyzeFixedRecordByMonthData
}