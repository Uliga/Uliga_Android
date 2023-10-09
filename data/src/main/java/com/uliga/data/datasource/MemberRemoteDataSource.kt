package com.uliga.data.datasource

interface MemberRemoteDataSource {

    suspend fun deleteMember(): String
}