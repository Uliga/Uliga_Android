package com.uliga.domain.usecase.accountbook.remote.analyze

import com.uliga.domain.model.accountBook.analyze.AccountBookAnalyzeRecordByDay
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookRecordByDayUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int
    ): Result<AccountBookAnalyzeRecordByDay> {
        return accountBookRepository.getAccountBookRecordByDay(id, year, month)
    }
}