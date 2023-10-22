package com.uliga.domain.usecase.member

import com.uliga.domain.repository.AccountBookRepository
import com.uliga.domain.repository.MemberRepository
import com.uliga.domain.repository.UserAuthRepository
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

        userAuthRepository.updateToken("")
        userAuthRepository.updateId(0L)
        accountBookRepository.updateCurrentAccountBookId(0L)
        accountBookRepository.updateCurrentAccountBookName("")

        return deleteResult
    }
}