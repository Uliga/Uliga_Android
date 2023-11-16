package com.uliga.domain.fakeRepository

import com.uliga.domain.model.member.Member
import com.uliga.domain.model.member.MemberInfo
import com.uliga.domain.model.member.MemberInvitation
import com.uliga.domain.model.member.MemberNotification
import com.uliga.domain.repository.MemberRepository
import java.util.UUID
import kotlin.random.Random

class FakeMemberRepository : MemberRepository {

    private val member = Member(
        memberInfo = MemberInfo(
            id = Random.nextLong(1, Long.MAX_VALUE),
            privateAccountBookId = Random.nextLong(1, Long.MAX_VALUE),
            userName = UUID.randomUUID().toString(),
            nickName = UUID.randomUUID().toString(),
            email = UUID.randomUUID().toString()
        ),
        invitations = listOf(
            MemberInvitation(
                id = Random.nextLong(1, Long.MAX_VALUE),
                memberName = UUID.randomUUID().toString(),
                accountBookName = UUID.randomUUID().toString()
            )
        ),
        notifications = listOf(
            MemberNotification(
                scheduleName = UUID.randomUUID().toString(),
                creatorName = UUID.randomUUID().toString(),
                value = Random.nextLong(1, Long.MAX_VALUE),
                day = Random.nextLong(1, 31)
            )
        )
    )

    private val deleteString = UUID.randomUUID().toString()

    override suspend fun deleteMember(): Result<String> {
        return runCatching { deleteString }
    }

    override suspend fun getMember(): Result<Member> {
        return runCatching { member }
    }
}