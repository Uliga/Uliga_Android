package com.uliga.domain

import com.google.common.truth.Truth.assertThat
import com.uliga.domain.fakeRepository.FakeMemberRepository
import com.uliga.domain.usecase.member.GetMemberUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMemberTest {

    private lateinit var getMember: GetMemberUseCase

    private lateinit var fakeMemberRepository: FakeMemberRepository

    @Before
    fun setUp() {
        fakeMemberRepository = FakeMemberRepository()

        getMember = GetMemberUseCase(fakeMemberRepository)


    }

    @Test
    fun `Get and check MemberInfo`(): Unit = runBlocking {
        getMember().onSuccess {
            assertThat(it.memberInfo.id).isAtLeast(1L)
            assertThat(it.memberInfo.email).isNotEmpty()
            assertThat(it.memberInfo.nickName).isNotEmpty()
            assertThat(it.memberInfo.userName).isNotEmpty()
            assertThat(it.memberInfo.privateAccountBookId).isAtLeast(1L)
        }
    }

    @Test
    fun `Get and check invitations`(): Unit = runBlocking {
        getMember().onSuccess {
            assertThat(it.invitations[0].id).isAtLeast(1L)
            assertThat(it.invitations[0].memberName).isNotEmpty()
            assertThat(it.invitations[0].accountBookName).isNotEmpty()
        }
    }

    @Test
    fun `Get and check notifications`(): Unit = runBlocking {
        getMember().onSuccess {
            assertThat(it.notifications[0].day).isAtLeast(1L)
            assertThat(it.notifications[0].day).isAtMost(31L)
            assertThat(it.notifications[0].creatorName).isNotEmpty()
            assertThat(it.notifications[0].scheduleName).isNotEmpty()
            assertThat(it.notifications[0].value).isAtLeast(1L)
        }
    }
}