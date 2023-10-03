package com.uliga.domain.usecase

import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.SocialLoginRequest
import com.uliga.domain.repository.UserAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostSocialLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository
) {
    suspend operator fun invoke(socialLoginRequest: SocialLoginRequest): Result<LoginResponse> {
        val socialLoginResult = userAuthRepository.postSocialLogin(socialLoginRequest)
        val accessToken = socialLoginResult.getOrThrow().tokenInfo.accessToken
        val id = socialLoginResult.getOrThrow().memberInfo.id

        withContext(Dispatchers.IO) {
            joinAll(
                launch { userAuthRepository.updateToken(accessToken) },
                launch { userAuthRepository.updateId(id) }
            )
        }

        return socialLoginResult
    }
}