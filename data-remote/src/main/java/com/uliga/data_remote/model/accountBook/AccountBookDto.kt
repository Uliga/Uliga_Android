package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookDto(
    val info: AccountBookInfoDto,
    val numberOfMember: AccountBookNumberOfMemberDto,
    val members: List<AccountBookMemberDto>,
    val categories: List<AccountBookCategoryDto>
)

fun AccountBookDto.toData() = AccountBookData(
    info = info.toData(),
    numberOfMember = numberOfMember.toData(),
    members = members.map { it.toData() },
    categories = categories.map { it.toData() }
)

fun AccountBookData.toDto() = AccountBookDto(
    info = info.toDto(),
    numberOfMember = numberOfMember.toDto(),
    members = members.map { it.toDto() },
    categories = categories.map { it.toDto() }
)