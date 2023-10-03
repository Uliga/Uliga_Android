package com.uliga.data_remote

import com.uliga.data.datasource.AccountBookRemoteDataSource
import com.uliga.data.datasource.UserAuthRemoteDataSource
import com.uliga.data_remote.datasource.AccountBookRemoteDataSourceImpl
import com.uliga.data_remote.datasource.UserAuthRemoteDataSourceImpl
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
    fun bindUserAuthRemoteDataSource(
        impl: UserAuthRemoteDataSourceImpl
    ): UserAuthRemoteDataSource

    @Singleton
    @Binds
    fun bindAccountBookRemoteDataSource(
        impl: AccountBookRemoteDataSourceImpl
    ): AccountBookRemoteDataSource
}