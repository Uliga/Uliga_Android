package com.uliga.domain.usecase.accountbook.remote

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAccountBookDayTransactionUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(accountBookTransactionIds: AccountBookTransactionIds): Result<String> {
        return accountBookRepository.deleteAccountBookDayTransaction(accountBookTransactionIds)
    }
}