package com.uliga.domain.usecase.accountbook.local

import com.uliga.domain.repository.AccountBookRepository
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FetchCurrentAccountBookIdUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke() = channelFlow {
        accountBookRepository.accountBookId.collectLatest {
            send(it)
        }
    }
}