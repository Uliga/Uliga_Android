package com.uliga.data.repository

import com.uliga.data.datasource.MemberRemoteDataSource
import com.uliga.data.model.member.toDomain
import com.uliga.domain.model.member.Member
import com.uliga.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val remote: MemberRemoteDataSource
) : MemberRepository {

    /**
     * Remote
     */

    override suspend fun deleteMember(): Result<String> {
        return runCatching {
            remote.deleteMember()
        }
    }

    override suspend fun getMember(): Result<Member> {
        return runCatching {
            remote.getMember().toDomain()
        }
    }
}