package com.uliga.domain.usecase.userAuth.local

import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateIsLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(isLogin: Boolean): Result<Unit> {
        return userAuthRepository.updateIsLogin(isLogin)
    }
}