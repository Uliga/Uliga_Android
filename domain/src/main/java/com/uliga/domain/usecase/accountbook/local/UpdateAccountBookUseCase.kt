package com.uliga.domain.usecase.accountbook.local

import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UpdateAccountBookUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(accountBookName: String, accountBookId: Long): Result<Unit> {
        return runCatching {
            with(accountBookRepository) {
                updateCurrentAccountBookName(accountBookName)
                updateCurrentAccountBookId(accountBookId)
            }
        }
    }
}