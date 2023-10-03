package com.uliga.domain.usecase

import com.uliga.domain.model.LoginResponse
import com.uliga.domain.model.NormalLoginRequest
import com.uliga.domain.repository.UserAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostNormalLoginUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository

) {
    suspend operator fun invoke(normalLoginRequest: NormalLoginRequest): Result<LoginResponse> {
        val normalLoginResult = userAuthRepository.postNormalLogin(normalLoginRequest)
        val accessToken = normalLoginResult.getOrThrow().tokenInfo.accessToken
        val id = normalLoginResult.getOrThrow().memberInfo.id

        withContext(Dispatchers.IO) {
            joinAll(
                launch { userAuthRepository.updateToken(accessToken) },
                launch { userAuthRepository.updateId(id) }
            )
        }

        return normalLoginResult
    }
}