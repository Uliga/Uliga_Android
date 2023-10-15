package com.uliga.data_remote.model.member

import com.uliga.data.model.member.MemberInfoData
import kotlinx.serialization.Serializable

@Serializable
data class MemberInfoDto(
    val id: Long,
    val privateAccountBookId: Long,
    val userName: String,
    val nickName: String,
    val email: String,
)

fun MemberInfoData.toDto() = MemberInfoDto(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)


fun MemberInfoDto.toData() = MemberInfoData(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)
