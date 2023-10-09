package com.uliga.data_local

import com.uliga.data.datasource.AccountBookLocalDataSource
import com.uliga.data.datasource.UserAuthLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal interface LocalDataSourceModule {

    @Singleton
    @Binds
    fun bindUserLocalDataSource(
        impl: UserAuthLocalDataSourceImpl
    ): UserAuthLocalDataSource

    @Singleton
    @Binds
    fun bindAccountBookLocalDataSource(
        impl: AccountBookLocalDataSourceImpl
    ): AccountBookLocalDataSource
}