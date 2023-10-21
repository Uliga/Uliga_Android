package com.uliga.data.repository

import com.uliga.data.datasource.AccountBookLocalDataSource
import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.analyze.byDay.toDomain
import com.uliga.data.model.accountBook.analyze.byMonth.category.toDomain
import com.uliga.data.model.accountBook.analyze.byMonth.compare.toDomain
import com.uliga.data.model.accountBook.analyze.byMonth.schedule.toDomain
import com.uliga.data.model.accountBook.analyze.byWeek.toDomain
import com.uliga.data.model.accountBook.asset.day.toDomain
import com.uliga.data.model.accountBook.asset.month.toDomain
import com.uliga.data.model.accountBook.asset.toDomain
import com.uliga.data.model.accountBook.budget.toData
import com.uliga.data.model.accountBook.budget.toDomain
import com.uliga.data.model.accountBook.financeSchedule.toData
import com.uliga.data.model.accountBook.financeSchedule.toDomain
import com.uliga.data.model.accountBook.invitation.toData
import com.uliga.data.model.accountBook.invitation.toDomain
import com.uliga.data.model.accountBook.toData
import com.uliga.data.model.accountBook.toDomain
import com.uliga.data.model.accountBook.transaction.toData
import com.uliga.data.model.accountBook.transaction.toDomain
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
import javax.inject.Inject

class AccountBookRepositoryImpl @Inject constructor(
    private val remote: AccountBookRemoteDataSource,
    private val local: AccountBookLocalDataSource
) : AccountBookRepository {
    override suspend fun getAccountBooks(): Result<AccountBooks> {
        return runCatching {
            remote.getAccountBooks().toDomain()
        }
    }

    override suspend fun postAccountBook(accountBookGenerationRequest: AccountBookGenerationRequest): Result<AccountBookGenerationResponse> {
        return runCatching {
            remote.postAccountBook(accountBookGenerationRequest.toData()).toDomain()
        }
    }

    override suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequest: AccountBookFinanceScheduleRequest): Result<AccountBookFinanceScheduleResponse> {
        return runCatching {
            remote.postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequest.toData())
                .toDomain()
        }
    }

    override suspend fun postAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse> {
        return runCatching {
            remote.postAccountBookBudget(accountBookBudgetRequest.toData()).toDomain()
        }
    }

    override suspend fun patchAccountBookBudget(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse> {
        return runCatching {
            remote.patchAccountBookBudget(accountBookBudgetRequest.toData()).toDomain()
        }
    }

    override suspend fun getAccountBookMonthAsset(
        accountBookId: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAsset> {
        return runCatching {
            remote.getAccountBookMonthAsset(accountBookId, year, month).toDomain()
        }
    }

    override suspend fun postAccountBookInvitationReply(accountBookInvitationReply: AccountBookInvitationReply): Result<AccountBookInvitationReply> {
        return runCatching {
            remote.postAccountBookInvitationReply(accountBookInvitationReply.toData()).toDomain()
        }
    }

    override suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequest: AccountBookTransactionRequest
    ): Result<AccountBookTransactionResponse> {
        return runCatching {
            remote.postAccountBookTransaction(
                transactionType,
                accountBookTransactionRequest.toData()
            ).toDomain()
        }
    }

    override suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAssetMonth> {
        return runCatching {
            remote.getAccountBookMonthTransaction(
                id, year, month
            ).toDomain()
        }
    }

    override suspend fun getAccountBookDayTransaction(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): Result<AccountBookAssetDay> {
        return runCatching {
            remote.getAccountBookDayTransaction(
                id, year, month, day
            ).toDomain()
        }
    }

    override suspend fun getAccountBookRecordByDay(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByDay> {
        return runCatching {
            remote.getAccountBookRecordByDay(id, year, month).toDomain()
        }
    }

    override suspend fun getAccountBookRecordByWeek(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): Result<AccountBookAnalyzeRecordByWeek> {
        return runCatching {
            remote.getAccountBookRecordByWeek(id, year, month, startDay).toDomain()
        }
    }

    override suspend fun getAccountBookRecordByMonthForCompare(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeByMonthForCompare> {
        return runCatching {
            remote.getAccountBookRecordByMonthForCompare(id, year, month).toDomain()
        }
    }

    override suspend fun getAccountBookRecordByMonthForCategory(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByMonthForCategory> {
        return runCatching {
            remote.getAccountBookRecordByMonthForCategory(id, year, month).toDomain()
        }
    }

    override suspend fun getAccountBookFixedRecordByMonth(id: Long): Result<AccountBookAnalyzeFixedRecordByMonth> {
        return runCatching {
            remote.getAccountBookFixedRecordByMonth(id).toDomain()
        }
    }

    override suspend fun deleteAccountBookDayTransaction(accountBookTransactionIdsBookIds: AccountBookTransactionIds): Result<String> {
        return runCatching {
            remote.deleteAccountBookDayTransaction(accountBookTransactionIdsBookIds.toData())
        }
    }

    override suspend fun updateCurrentAccountBookName(accountBookName: String): Result<Unit> {
        return runCatching {
            local.updateCurrentAccountBookName(accountBookName)
        }
    }

    override suspend fun getCurrentAccountBookName(): Result<String> {
        return runCatching {
            local.getCurrentAccountBookName()
        }
    }

    override suspend fun updateCurrentAccountBookId(accountBookId: Long): Result<Unit> {
        return runCatching {
            local.updateCurrentAccountBookId(accountBookId)
        }
    }

    override suspend fun getCurrentAccountBookId(): Result<Long> {
        return runCatching {
            local.getCurrentAccountBookId()
        }
    }
}