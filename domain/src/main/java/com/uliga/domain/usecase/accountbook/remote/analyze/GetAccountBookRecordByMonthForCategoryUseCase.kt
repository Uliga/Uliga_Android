package com.uliga.domain.usecase.accountbook.remote.analyze

import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategory
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookRecordByMonthForCategoryUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByMonthForCategory> {
        return accountBookRepository.getAccountBookRecordByMonthForCategory(id, year, month)
    }
}