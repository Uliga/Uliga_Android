package com.uliga.domain.repository

import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponse

interface AccountBookRepository {

    /**
     * Remote
     */

    suspend fun getAccountBooks(): Result<AccountBooks>

    suspend fun postAccountBook(accountBookGenerationRequest: AccountBookGenerationRequest): Result<AccountBookGenerationResponse>

    suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequest: AccountBookFinanceScheduleRequest): Result<AccountBookFinanceScheduleResponse>

    /**
     * Local
     */

    suspend fun updateCurrentAccountBookName(accountBookName: String): Result<Unit>

    suspend fun getCurrentAccountBookName(): Result<String>

    suspend fun updateCurrentAccountBookId(accountBookId: Long): Result<Unit>

    suspend fun getCurrentAccountBookId(): Result<Long>
}