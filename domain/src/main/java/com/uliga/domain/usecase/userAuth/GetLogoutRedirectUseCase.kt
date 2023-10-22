package com.uliga.domain.usecase.userAuth

import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLogoutRedirectUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(): Result<String> {
        return userAuthRepository.getLogoutRedirect()
    }
}