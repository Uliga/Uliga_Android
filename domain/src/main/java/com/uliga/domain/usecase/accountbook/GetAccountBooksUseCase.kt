package com.uliga.domain.usecase.accountbook

import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAccountBooksUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(): Result<AccountBooks> {
        return accountBookRepository.getAccountBooks()
    }
}