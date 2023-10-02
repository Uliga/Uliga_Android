package com.uliga.data_remote

import com.uliga.data.UserAuthRemoteDataSource
import com.uliga.data.model.UserAuthEmailExistedData
import com.uliga.data_remote.model.UserAuthEmailExistedDto
import com.uliga.data_remote.model.toData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import javax.inject.Inject

class UserAuthRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
): UserAuthRemoteDataSource {
    override suspend fun getUserAuthEmailExisted(email: String): UserAuthEmailExistedData {
        return client.get {
            url.path(Path.AUTH, Path.MAIL, Path.EXISTS, email)
        }.body<UserAuthEmailExistedDto>().toData()
    }


}