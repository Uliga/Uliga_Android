package com.uliga.domain.usecase

import com.uliga.domain.model.UserAuthDataExisted
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserAuthDataExistedUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(type: String, data: String): Result<UserAuthDataExisted> {
        return userAuthRepository.getUserAuthDataExisted(type, data)
    }
}