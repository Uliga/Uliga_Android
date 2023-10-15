package com.uliga.domain.repository

import com.uliga.domain.model.member.Member

interface MemberRepository {

    suspend fun deleteMember(): Result<String>

    suspend fun getMember(): Result<Member>
}