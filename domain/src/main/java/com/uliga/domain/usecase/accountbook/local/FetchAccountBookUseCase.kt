package com.uliga.domain.usecase.accountbook.local

import com.uliga.domain.repository.AccountBookRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FetchAccountBookUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
){
    suspend operator fun invoke(): Result<Pair<String, Long>> {

        return runCatching {
            val accountBookName = accountBookRepository.getCurrentAccountBookName().getOrThrow()
            val accountBookId = accountBookRepository.getCurrentAccountBookId().getOrThrow()

            Pair(accountBookName, accountBookId)
        }
    }
}