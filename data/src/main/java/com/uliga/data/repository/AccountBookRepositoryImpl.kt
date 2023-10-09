package com.uliga.data.repository

import com.uliga.data.datasource.AccountBookLocalDataSource
import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.toData
import com.uliga.data.model.accountBook.toDomain
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import com.uliga.domain.model.accountBook.AccountBooks
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