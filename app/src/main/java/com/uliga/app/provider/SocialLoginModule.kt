package com.uliga.app.provider

import com.uliga.domain.SocialLoginProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface SocialLoginModule {

    @Binds
    fun bindKakaoLoginProvider(
        provider: KakaoLoginProviderImpl
    ): SocialLoginProvider
}