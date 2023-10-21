package com.uliga.domain.repository

import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDay
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategory
import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonth
import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetDay
import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetMonth
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetResponse
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponse
import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionResponse

interface AccountBookRepository {

    /**
     * Remote
     */

    suspend fun getAccountBooks(): Result<AccountBooks>

    suspend fun postAccountBook(accountBookGenerationRequest: AccountBookGenerationRequest): Result<AccountBookGenerationResponse>

    suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequest: AccountBookFinanceScheduleRequest): Result<AccountBookFinanceScheduleResponse>

    suspend fun postAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse>

    suspend fun patchAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse>

    suspend fun getAccountBookMonthAsset(
        accountBookId: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAsset>

    suspend fun postAccountBookInvitationReply(accountBookInvitationReply: AccountBookInvitationReply): Result<AccountBookInvitationReply>

    suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequest: AccountBookTransactionRequest
    ): Result<AccountBookTransactionResponse>

    suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAssetMonth>

    suspend fun getAccountBookDayTransaction(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): Result<AccountBookAssetDay>

    suspend fun getAccountBookRecordByDay(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByDay>

    suspend fun getAccountBookRecordByWeek(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): Result<AccountBookAnalyzeRecordByWeek>

    suspend fun getAccountBookRecordByMonthForCompare(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeByMonthForCompare>

    suspend fun getAccountBookRecordByMonthForCategory(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByMonthForCategory>

    suspend fun getAccountBookFixedRecordByMonth(
        id: Long
    ): Result<AccountBookAnalyzeFixedRecordByMonth>

    suspend fun deleteAccountBookDayTransaction(
        accountBookTransactionIdsBookIds: AccountBookTransactionIds
    ): Result<String>

    /**
     * Local
     */

    suspend fun updateCurrentAccountBookName(accountBookName: String): Result<Unit>

    suspend fun getCurrentAccountBookName(): Result<String>

    suspend fun updateCurrentAccountBookId(accountBookId: Long): Result<Unit>

    suspend fun getCurrentAccountBookId(): Result<Long>
}