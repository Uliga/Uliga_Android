package com.uliga.app.provider

import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap


@Module
@InstallIn(SingletonComponent::class)
interface SocialLoginModule {

    @Binds
    fun bindSocialLoginProvider(
        provider: SocialLoginProviderImpl
    ): SocialLoginProvider

    @Binds
    @IntoMap
    @SocialLoginKey(type = AuthType.GOOGLE)
    fun bindGoogleLoginProvider(
        provider: GoogleLoginProviderImpl
    ): SocialLoginProvider


    @Binds
    @IntoMap
    @SocialLoginKey(type = AuthType.KAKAO)
    fun bindKakaoLoginProvider(
        provider: KakaoLoginProviderImpl
    ): SocialLoginProvider
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class SocialLoginKey(
    val type: AuthType
)