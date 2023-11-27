package com.uliga.domain.usecase.userAuth.remote

import com.uliga.domain.model.userAuth.LoginResponse
import com.uliga.domain.model.userAuth.NormalLoginRequest
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
                launch { userAuthRepository.updateId(id) },
            )
        }

        return normalLoginResult
    }
}