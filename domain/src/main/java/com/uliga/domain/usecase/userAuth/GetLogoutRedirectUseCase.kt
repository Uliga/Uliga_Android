package com.uliga.domain.usecase.userAuth

import com.uliga.domain.repository.AccountBookRepository
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLogoutRedirectUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(): Result<String> {

        val logoutResult = userAuthRepository.getLogoutRedirect()

        userAuthRepository.updateToken("")
        userAuthRepository.updateId(0L)
        accountBookRepository.updateCurrentAccountBookId(0L)
        accountBookRepository.updateCurrentAccountBookName("")

        return logoutResult
    }
}