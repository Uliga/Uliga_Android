package com.uliga.data.repository

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.model.accountBook.toDomain
import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject

class AccountBookRepositoryImpl @Inject constructor(
    private val accountBookRemoteDataSource: AccountBookRemoteDataSource
) : AccountBookRepository {
    override suspend fun getAccountBooks(): Result<AccountBooks> {
        return runCatching {
            accountBookRemoteDataSource.getAccountBooks().toDomain()
        }
    }
}