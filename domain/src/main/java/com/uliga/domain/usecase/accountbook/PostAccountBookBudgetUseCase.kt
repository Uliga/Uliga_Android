package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetResponse
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostAccountBookBudgetUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(accountBookBudgetRequest: AccountBookBudgetRequest): Result<AccountBookBudgetResponse> {
        return accountBookRepository.postAccountBookBudget(accountBookBudgetRequest)
    }
}