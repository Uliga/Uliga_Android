package com.uliga.data.di

import com.uliga.data.repository.UserAuthRepositoryImpl
import com.uliga.domain.repository.UserAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindUserAUthRepository(
        impl: UserAuthRepositoryImpl
    ): UserAuthRepository
}