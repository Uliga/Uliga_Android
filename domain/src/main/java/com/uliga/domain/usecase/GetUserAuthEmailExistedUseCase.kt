package com.uliga.domain.usecase

import com.uliga.domain.model.UserAuthEmailExisted
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserAuthEmailExistedUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(email: String): Result<UserAuthEmailExisted> {
        return userAuthRepository.getUserAuthEmailExisted(email)
    }
}