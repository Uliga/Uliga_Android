package com.uliga.domain.usecase.member

import com.uliga.domain.model.member.Member
import com.uliga.domain.repository.MemberRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Result<Member> {
        return memberRepository.getMember()
    }
}