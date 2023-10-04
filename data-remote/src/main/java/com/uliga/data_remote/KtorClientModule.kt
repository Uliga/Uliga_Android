package com.uliga.data_remote

import android.util.Log
import com.uliga.data.datasource.UserAuthLocalDataSource
import com.uliga.data_remote.model.userAuth.ReissueRequestDto
import com.uliga.data_remote.model.userAuth.ReissueResponseDto
import com.uliga.data_remote.model.userAuth.toData
import com.uliga.data_remote.model.userAuth.toDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.append
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
internal object KtorClientModule {

    private const val TAG = "HTTP Client"

    @Provides
    fun provideKtorHttpClient(
        userAuthLocalDataSource: UserAuthLocalDataSource
    ) = HttpClient(OkHttp) {

        val hostUrl = "uliga.site"

        engine {
            config {
                connectTimeout(timeout = 60, unit = TimeUnit.SECONDS)

            }
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.w(TAG, message)
                }
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = hostUrl
            }
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json)

                runBlocking {
                    val accessToken = userAuthLocalDataSource.getToken()
                    if (accessToken != "") {
                        append(HttpHeaders.Authorization, "Bearer $accessToken")
                    }
                }
            }

        }

        install(Auth) {
            bearer {
                refreshTokens {
                    val token = runBlocking { userAuthLocalDataSource.getToken() }
                    val reissueRequestDto = ReissueRequestDto(
                        token = token
                    )

                    val reissueResponse = client.post {
                        markAsRefreshTokenRequest()
                        url.path(Path.AUTH, Path.REISSUE)

                        header("Authorization", "")
                        setBody(reissueRequestDto)
                    }.body<ReissueResponseDto>()

                    runBlocking { userAuthLocalDataSource.updateToken(reissueResponse.accessToken) }

                    BearerTokens(
                        accessToken = reissueResponse.accessToken,
                        refreshToken = reissueResponse.accessToken
                    )
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = true
                    ignoreUnknownKeys = true
                }
            )
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status.value.isNotSuccess()) {

                }
            }
        }
    }
}