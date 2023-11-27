package com.uliga.domain.usecase.userAuth.local

import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchIsLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(): Result<Boolean> {
        return userAuthRepository.getIsLogin()
    }
}