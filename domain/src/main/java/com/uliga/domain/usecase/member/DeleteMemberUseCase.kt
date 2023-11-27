package com.uliga.domain.usecase.member

import com.uliga.domain.repository.AccountBookRepository
import com.uliga.domain.repository.MemberRepository
import com.uliga.domain.repository.UserAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
    private val userAuthRepository: UserAuthRepository,
    private val accountBookRepository: AccountBookRepository
) {
    suspend operator fun invoke(): Result<String> {

        val deleteResult = memberRepository.deleteMember()

        withContext(Dispatchers.IO) {
            joinAll(
                launch { userAuthRepository.updateToken("") },
                launch { userAuthRepository.updateId(0L) },
                launch { userAuthRepository.updateIsLogin(false) },
                launch { accountBookRepository.updateCurrentAccountBookId(0L) },
                launch { accountBookRepository.updateCurrentAccountBookName("") }
            )
        }

        return deleteResult
    }
}