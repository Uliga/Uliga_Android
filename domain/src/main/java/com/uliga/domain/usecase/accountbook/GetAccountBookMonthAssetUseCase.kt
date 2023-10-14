package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookMonthAssetUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        accountBookId: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAsset> {
        return accountBookRepository.getAccountBookMonthAsset(accountBookId, year, month)
    }
}