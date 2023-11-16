package com.uliga.domain

import com.google.common.truth.Truth.assertThat
import com.uliga.domain.fakeRepository.FakeAccountBookRepository
import com.uliga.domain.fakeRepository.FakeUserAuthRepository
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.accountbook.local.UpdateAccountBookUseCase
import com.uliga.domain.usecase.userAuth.PostSocialLoginUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class FetchCurrentAccountBookInfoUseCaseTest {

    private lateinit var fakeAccountBookRepository: FakeAccountBookRepository

    private lateinit var fakeUserAuthRepository: FakeUserAuthRepository

    private lateinit var updateAccountBookInfoUseCase: UpdateAccountBookUseCase

    private lateinit var fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase

    private lateinit var postSocialLoginUseCase: PostSocialLoginUseCase

    @Before
    fun setUp() {
        fakeAccountBookRepository = FakeAccountBookRepository()

        fakeUserAuthRepository = FakeUserAuthRepository()

        updateAccountBookInfoUseCase = UpdateAccountBookUseCase(fakeAccountBookRepository)

        fetchCurrentAccountBookInfoUseCase = FetchCurrentAccountBookInfoUseCase(fakeAccountBookRepository)

        postSocialLoginUseCase = PostSocialLoginUseCase(fakeUserAuthRepository)
    }

    @Test
    fun `fetch Current AccountBook Info`(): Unit = runBlocking {

        repeat(100) {

            val fakeAccountBookName = UUID.randomUUID().toString()
            val fakeAccountBookId = Random.nextLong(1, Long.MAX_VALUE)

            updateAccountBookInfoUseCase(fakeAccountBookName, fakeAccountBookId)

            fetchCurrentAccountBookInfoUseCase().onSuccess {
                assertThat(it.first).isNotEmpty()
                assertThat(it.second).isAtLeast(1L)
            }
        }
    }

    @Test
    fun `post social Login`(): Unit = runBlocking {

        repeat(100) {

            val socialLoginRequest = SocialLoginRequest(
                email = UUID.randomUUID().toString(),
                userName = UUID.randomUUID().toString(),
                nickName = UUID.randomUUID().toString(),
                loginType = UUID.randomUUID().toString(),
                applicationPassword = UUID.randomUUID().toString()
            )

            postSocialLoginUseCase(socialLoginRequest).onSuccess {
                assertThat(it.memberInfo.id).isAtLeast(1L)
                assertThat(it.memberInfo.email).matches(socialLoginRequest.email)
                assertThat(it.memberInfo.privateAccountBookId).isAtLeast(1L)
                assertThat(it.memberInfo.nickName).isEqualTo(socialLoginRequest.nickName)
                assertThat(it.memberInfo.userName).isEqualTo(socialLoginRequest.userName)

                assertThat(it.tokenInfo.accessToken).isNotEmpty()
                assertThat(it.tokenInfo.accessTokenExpiresIn).isAtLeast(1L)
                assertThat(it.tokenInfo.grantType).isNotEmpty()
            }
        }


    }


}

