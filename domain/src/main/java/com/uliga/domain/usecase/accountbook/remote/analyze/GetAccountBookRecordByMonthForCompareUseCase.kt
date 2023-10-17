package com.uliga.domain.usecase.accountbook.remote.analyze

import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject

class GetAccountBookRecordByMonthForCompareUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeByMonthForCompare> {
        return accountBookRepository.getAccountBookRecordByMonthForCompare(id, year, month)
    }
}