package com.uliga.domain.usecase.member

import com.uliga.domain.repository.MemberRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Result<String> {
        return memberRepository.deleteMember()
    }
}