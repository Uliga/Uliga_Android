package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookMember
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookMemberData(
    val id: Long,
    val username: String,
    val accountBookAuthority: String,
    val avatarUrl: String,
    val email: String,
)

fun AccountBookMemberData.toDomain() = AccountBookMember(
    id = id,
    username = username,
    accountBookAuthority = accountBookAuthority,
    avatarUrl = avatarUrl,
    email = email
)

fun AccountBookMember.toData() = AccountBookMemberData(
    id = id,
    username = username,
    accountBookAuthority = accountBookAuthority,
    avatarUrl = avatarUrl,
    email = email
)