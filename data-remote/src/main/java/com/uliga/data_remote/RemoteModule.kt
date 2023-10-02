package com.uliga.data_remote

import com.uliga.data.UserAuthRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteModule {

    @Singleton
    @Binds
    fun bindUserAuthEmoteDataSource(
        impl: UserAuthRemoteDataSourceImpl
    ): UserAuthRemoteDataSource
}