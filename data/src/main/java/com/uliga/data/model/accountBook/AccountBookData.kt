package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBook
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookData(
    val info: AccountBookInfoData,
    val numberOfMember: AccountBookNumberOfMemberData,
    val members: List<AccountBookMemberData>,
    val categories: List<AccountBookCategoryData>
)

fun AccountBookData.toDomain() = AccountBook(
    info = info.toDomain(),
    numberOfMember = numberOfMember.toDomain(),
    members = members.map { it.toDomain() },
    categories = categories.map { it.toDomain() }
)

fun AccountBook.toData() = AccountBookData(
    info = info.toData(),
    numberOfMember = numberOfMember.toData(),
    members = members.map { it.toData() },
    categories = categories.map { it.toData() }
)