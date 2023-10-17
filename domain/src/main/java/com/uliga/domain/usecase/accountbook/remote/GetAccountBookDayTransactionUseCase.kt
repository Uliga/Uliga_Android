package com.uliga.domain.usecase.accountbook.remote

import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetDay
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class GetAccountBookDayTransactionUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int,
        day: Int
    ): Result<AccountBookAssetDay> {
        return accountBookRepository.getAccountBookDayTransaction(id, year, month, day)
    }
}