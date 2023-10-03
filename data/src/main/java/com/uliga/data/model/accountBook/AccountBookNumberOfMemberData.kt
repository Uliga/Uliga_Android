package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookNumberOfMember
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookNumberOfMemberData(
    val count: Long
)

fun AccountBookNumberOfMemberData.toDomain() = AccountBookNumberOfMember(
    count = count
)

fun AccountBookNumberOfMember.toData() = AccountBookNumberOfMemberData(
    count = count
)