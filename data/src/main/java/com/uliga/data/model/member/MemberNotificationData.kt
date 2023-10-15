package com.uliga.data.model.member

import com.uliga.domain.model.member.MemberNotification
import kotlinx.serialization.Serializable

@Serializable
data class MemberNotificationData(
    val scheduleName: String,
    val creatorName: String,
    val value: Long,
    val day: Long
)

fun MemberNotification.toData() = MemberNotificationData(
    scheduleName = scheduleName,
    creatorName = creatorName,
    value = value,
    day = day
)

fun MemberNotificationData.toDomain() = MemberNotification(
    scheduleName = scheduleName,
    creatorName = creatorName,
    value = value,
    day = day
)