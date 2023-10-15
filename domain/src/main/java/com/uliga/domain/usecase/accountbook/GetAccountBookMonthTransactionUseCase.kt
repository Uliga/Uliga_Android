package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetMonth
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookMonthTransactionUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAssetMonth> {
        return accountBookRepository.getAccountBookMonthTransaction(id, year, month)
    }
}