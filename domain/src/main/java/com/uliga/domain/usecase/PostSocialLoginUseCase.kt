package com.uliga.domain.usecase

import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.SocialLoginRequest
import com.uliga.domain.repository.UserAuthRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostSocialLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(socialLoginRequest: SocialLoginRequest): Result<LoginResponse> {
        return userAuthRepository.postSocialLogin(socialLoginRequest)
    }
}