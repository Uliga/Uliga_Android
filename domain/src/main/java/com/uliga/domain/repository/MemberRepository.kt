package com.uliga.domain.repository

interface MemberRepository {

    suspend fun deleteMember(): Result<String>
}