package com.uliga.domain.usecase.accountbook.remote.analyze

import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonth
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBookFixedRecordByMonthUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(id: Long): Result<AccountBookAnalyzeFixedRecordByMonth> {
        return accountBookRepository.getAccountBookFixedRecordByMonth(id)
    }
}