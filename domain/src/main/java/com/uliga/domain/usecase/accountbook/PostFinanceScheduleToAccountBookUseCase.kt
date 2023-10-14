package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponse
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostFinanceScheduleToAccountBookUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(accountBookFinanceScheduleRequest: AccountBookFinanceScheduleRequest): Result<AccountBookFinanceScheduleResponse> {
        return accountBookRepository.postFinanceScheduleToAccountBook(
            accountBookFinanceScheduleRequest
        )
    }
}