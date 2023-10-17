package com.uliga.domain.usecase.accountbook.remote.analyze

import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookRecordByWeekUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(
        id: Long,
        year: Int,
        month: Int,
        startDay: Int
    ): Result<AccountBookAnalyzeRecordByWeek> {
        return accountBookRepository.getAccountBookRecordByWeek(id, year, month, startDay)
    }
}