package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookInfoData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookInfoDto(
    val accountBookId: Long,
    val isPrivate: Boolean,
    val accountBookName: String,
    val accountBookAuthority: String,
    val relationShip: String,
    val getNotification: Boolean,
    val avatarUrl: String,
)

fun AccountBookInfoDto.toData() = AccountBookInfoData(
    accountBookId = accountBookId,
    isPrivate = isPrivate,
    accountBookName = accountBookName,
    accountBookAuthority = accountBookAuthority,
    relationShip = relationShip,
    getNotification = getNotification,
    avatarUrl = avatarUrl
)

fun AccountBookInfoData.toDto() = AccountBookInfoDto(
    accountBookId = accountBookId,
    isPrivate = isPrivate,
    accountBookName = accountBookName,
    accountBookAuthority = accountBookAuthority,
    relationShip = relationShip,
    getNotification = getNotification,
    avatarUrl = avatarUrl
)