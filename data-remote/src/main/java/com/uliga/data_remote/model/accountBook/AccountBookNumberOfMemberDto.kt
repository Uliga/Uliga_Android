package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookNumberOfMemberData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookNumberOfMemberDto(
    val count: Long
)

fun AccountBookNumberOfMemberDto.toData() = AccountBookNumberOfMemberData(
    count = count
)

fun AccountBookNumberOfMemberData.toDto() = AccountBookNumberOfMemberDto(
    count = count
)