package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookInfo
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookInfoData(
    val accountBookId: Long,
    val isPrivate: Boolean,
    val accountBookName: String,
    val accountBookAuthority: String,
    val relationShip: String,
    val getNotification: Boolean,
    val avatarUrl: String,
)

fun AccountBookInfoData.toDomain() = AccountBookInfo(
    accountBookId = accountBookId,
    isPrivate = isPrivate,
    accountBookName = accountBookName,
    accountBookAuthority = accountBookAuthority,
    relationShip = relationShip,
    getNotification = getNotification,
    avatarUrl = avatarUrl
)

fun AccountBookInfo.toData() = AccountBookInfoData(
    accountBookId = accountBookId,
    isPrivate = isPrivate,
    accountBookName = accountBookName,
    accountBookAuthority = accountBookAuthority,
    relationShip = relationShip,
    getNotification = getNotification,
    avatarUrl = avatarUrl
)