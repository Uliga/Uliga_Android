package com.uliga.data.datasource

import com.uliga.data.model.member.MemberData

interface MemberRemoteDataSource {

    suspend fun deleteMember(): String

    suspend fun getMember(): MemberData

}