package com.uliga.domain.usecase.accountbook.remote

import com.uliga.domain.repository.AccountBookRepository
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentAccountBookAssetUseCase @Inject constructor(
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke() = channelFlow {
        accountBookRepository.accountBookAsset.collectLatest {
            send(it)
        }
    }
}