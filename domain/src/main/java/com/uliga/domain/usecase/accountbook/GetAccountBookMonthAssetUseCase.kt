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
        isCurrent: Boolean,
        accountBookId: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAsset> {
        return accountBookRepository.getAccountBookMonthAsset(isCurrent, accountBookId, year, month)
    }
}