package com.uliga.domain.repository

import com.uliga.domain.model.accountBook.AccountBook
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import com.uliga.domain.model.accountBook.AccountBooks

interface AccountBookRepository {

    /**
     * Remote
     */

    suspend fun getAccountBooks(): Result<AccountBooks>

    suspend fun postAccountBook(accountBookGenerationRequest: AccountBookGenerationRequest): Result<AccountBookGenerationResponse>

    /**
     * Local
     */

    suspend fun updateCurrentAccountBookName(accountBookName: String): Result<Unit>

    suspend fun getCurrentAccountBookName(): Result<String>

    suspend fun updateCurrentAccountBookId(accountBookId: Long): Result<Unit>

    suspend fun getCurrentAccountBookId(): Result<Long>
}