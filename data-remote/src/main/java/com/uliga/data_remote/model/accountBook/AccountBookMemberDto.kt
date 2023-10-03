package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookMemberData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookMemberDto(
    val id: Long,
    val username: String,
    val accountBookAuthority: String,
    val avatarUrl: String,
    val email: String,
)

fun AccountBookMemberDto.toData() = AccountBookMemberData(
    id = id,
    username = username,
    accountBookAuthority = accountBookAuthority,
    avatarUrl = avatarUrl,
    email = email
)

fun AccountBookMemberData.toDto() = AccountBookMemberDto(
    id = id,
    username = username,
    accountBookAuthority = accountBookAuthority,
    avatarUrl = avatarUrl,
    email = email
)