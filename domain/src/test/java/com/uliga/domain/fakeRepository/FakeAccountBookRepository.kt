package com.uliga.domain.fakeRepository

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
import com.uliga.domain.repository.AccountBookRepository
import kotlinx.coroutines.flow.Flow

class FakeAccountBookRepository : AccountBookRepository {

    lateinit var fakeAccountBookName: String

    private var fakeAccountBookId: Long = 0L
    override val accountBookAsset: Flow<AccountBookAsset?>
        get() = TODO("Not yet implemented")

    override suspend fun getAccountBooks(): Result<AccountBooks> {
        TODO("Not yet implemented")
    }

    override suspend fun postAccountBook(accountBookGenerationRequest: AccountBookGenerationRequest): Result<AccountBookGenerationResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequest: AccountBookFinanceScheduleRequest): Result<AccountBookFinanceScheduleResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun postAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun patchAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookMonthAsset(
        isCurrent: Boolean,
        accountBookId: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAsset> {
        TODO("Not yet implemented")
    }



    override suspend fun postAccountBookInvitationReply(accountBookInvitationReply: AccountBookInvitationReply): Result<AccountBookInvitationReply> {
        TODO("Not yet implemented")
    }

    override suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequest: AccountBookTransactionRequest
    ): Result<AccountBookTransactionResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAssetMonth> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookDayTransaction(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): Result<AccountBookAssetDay> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookRecordByDay(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByDay> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookRecordByWeek(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): Result<AccountBookAnalyzeRecordByWeek> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookRecordByMonthForCompare(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeByMonthForCompare> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookRecordByMonthForCategory(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByMonthForCategory> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccountBookFixedRecordByMonth(id: Long): Result<AccountBookAnalyzeFixedRecordByMonth> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccountBookDayTransaction(accountBookTransactionIdsBookIds: AccountBookTransactionIds): Result<String> {
        TODO("Not yet implemented")
    }

    override val accountBookId: Flow<Long?>
        get() = TODO("Not yet implemented")
    override val accountBookName: Flow<String?>
        get() = TODO("Not yet implemented")

    override suspend fun updateCurrentAccountBookName(accountBookName: String): Result<Unit> {
        return runCatching { this.fakeAccountBookName = accountBookName }
    }


    override suspend fun getCurrentAccountBookName(): Result<String> {
        return runCatching { fakeAccountBookName }
    }

    override suspend fun updateCurrentAccountBookId(accountBookId: Long): Result<Unit> {
        return runCatching { this.fakeAccountBookId = accountBookId }
    }

    override suspend fun getCurrentAccountBookId(): Result<Long> {
        return runCatching { fakeAccountBookId }
    }
}