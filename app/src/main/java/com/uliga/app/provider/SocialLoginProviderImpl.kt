package com.uliga.app.provider

import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginProvider
import com.uliga.domain.SocialLoginResult
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class SocialLoginProviderImpl @Inject constructor(
    private val providers: Map<AuthType, @JvmSuppressWildcards Provider<SocialLoginProvider>>,
) : SocialLoginProvider {
    override suspend fun login(type: AuthType, checkedIdToken: String?, checkedEmail: String?): SocialLoginResult {
        return providers[type]?.run {
            get().login(type, checkedIdToken, checkedEmail)
        } ?: throw IllegalArgumentException("$type of parameter type is not allowed value.")
    }

    override suspend fun logout(type: AuthType) {
        providers.values.map { it.get().logout(type) }
    }
}