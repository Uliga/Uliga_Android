package com.uliga.data.repository

import com.uliga.data.datasource.AccountBookLocalDataSource
import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.asset.toDomain
import com.uliga.data.model.accountBook.budget.toData
import com.uliga.data.model.accountBook.budget.toDomain
import com.uliga.data.model.accountBook.financeSchedule.toData
import com.uliga.data.model.accountBook.financeSchedule.toDomain
import com.uliga.data.model.accountBook.invitation.toData
import com.uliga.data.model.accountBook.invitation.toDomain
import com.uliga.data.model.accountBook.toData
import com.uliga.data.model.accountBook.toDomain
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetResponse
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponse
import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
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