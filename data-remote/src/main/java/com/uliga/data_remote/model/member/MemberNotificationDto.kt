package com.uliga.data_remote.model.member

import com.uliga.data.model.member.MemberNotificationData
import kotlinx.serialization.Serializable

@Serializable
data class MemberNotificationDto(
    val scheduleName: String,
    val creatorName: String,
    val value: Long,
    val day: Long
)

fun MemberNotificationData.toDto() = MemberNotificationDto(
    scheduleName = scheduleName,
    creatorName = creatorName,
    value = value,
    day = day
)

fun MemberNotificationDto.toData() = MemberNotificationData(
    scheduleName = scheduleName,
    creatorName = creatorName,
    value = value,
    day = day
)