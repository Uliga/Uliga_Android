package com.uliga.data_local

import com.uliga.data.UserAuthLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface LocalModule {

    @Singleton
    @Binds
    fun bindUserLocalDataSource(
        impl: UserAuthLocalDataSourceImpl
    ): UserAuthLocalDataSource
}