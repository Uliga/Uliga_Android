package com.uliga.data.model.member

import com.uliga.domain.model.member.MemberInfo
import kotlinx.serialization.Serializable

@Serializable
data class MemberInfoData(
    val id: Long,
    val privateAccountBookId: Long,
    val userName: String,
    val nickName: String,
    val email: String,
)

fun MemberInfo.toData() = MemberInfoData(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)

fun MemberInfoData.toDomain() = MemberInfo(
    id = id,
    privateAccountBookId = privateAccountBookId,
    userName = userName,
    nickName = nickName,
    email = email
)
