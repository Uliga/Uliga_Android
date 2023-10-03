package com.uliga.data_remote.datasource

import com.uliga.data.datasource.UserAuthRemoteDataSource
import com.uliga.data.model.userAuth.LoginResponseData
import com.uliga.data.model.userAuth.NormalLoginRequestData
import com.uliga.data.model.userAuth.SocialLoginRequestData
import com.uliga.data.model.userAuth.UserAuthDataExistedData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.userAuth.LoginResponseDto
import com.uliga.data_remote.model.userAuth.UserAuthDataExistedDto
import com.uliga.data_remote.model.userAuth.toData
import com.uliga.data_remote.model.userAuth.toDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import javax.inject.Inject

class UserAuthRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : UserAuthRemoteDataSource {
    override suspend fun getUserAuthDataExisted(
        type: String,
        data: String
    ): UserAuthDataExistedData {
        val pathType = if (type == "mail") Path.MAIL else Path.NICKNAME

        return client.get {
            url.path(Path.AUTH, pathType, Path.EXISTS, data)
        }.body<UserAuthDataExistedDto>().toData()
    }

    override suspend fun postSocialLogin(socialLoginRequestData: SocialLoginRequestData): LoginResponseData {
        return client.post {
            url.path(Path.AUTH, Path.SOCIAL_LOGIN)
            setBody(socialLoginRequestData.toDto())
        }.body<LoginResponseDto>().toData()
    }

    override suspend fun postNormalLogin(normalLoginRequestData: NormalLoginRequestData): LoginResponseData {
        return client.post {
            url.path(Path.AUTH, Path.LOGIN)
            setBody(normalLoginRequestData.toDto())
        }.body<LoginResponseDto>().toData()
    }


}