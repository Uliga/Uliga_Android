package com.uliga.data.repository

import com.uliga.data.datasource.MemberRemoteDataSource
import com.uliga.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource
): MemberRepository {

    /**
     * Remote
     */

    override suspend fun deleteMember(): Result<String> {
        return runCatching {
            memberRemoteDataSource.deleteMember()
        }
    }
}