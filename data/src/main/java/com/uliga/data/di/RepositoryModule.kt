package com.uliga.data.di

import com.uliga.data.repository.AccountBookRepositoryImpl
import com.uliga.data.repository.MemberRepositoryImpl
import com.uliga.data.repository.UserAuthRepositoryImpl
import com.uliga.domain.repository.AccountBookRepository
import com.uliga.domain.repository.MemberRepository
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
    fun bindUserAuthRepository(
        impl: UserAuthRepositoryImpl
    ): UserAuthRepository

    @Singleton
    @Binds
    fun bindAccountBookRepository(
        impl: AccountBookRepositoryImpl
    ): AccountBookRepository

    @Singleton
    @Binds
    fun bindMemberRepository(
        impl: MemberRepositoryImpl
    ): MemberRepository
}