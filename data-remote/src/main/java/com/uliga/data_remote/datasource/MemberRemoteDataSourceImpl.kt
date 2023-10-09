package com.uliga.data_remote.datasource

import com.uliga.data.datasource.MemberRemoteDataSource
import com.uliga.data_remote.Path
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.http.path
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : MemberRemoteDataSource {
    override suspend fun deleteMember(): String {
        return client.delete {
            url.path(Path.MEMBER)
        }.body()
    }
}