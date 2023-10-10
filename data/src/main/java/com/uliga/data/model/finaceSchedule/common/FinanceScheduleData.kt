package com.uliga.data.model.finaceSchedule.common

import com.uliga.domain.model.finaceSchedule.common.FinanceSchedule
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleData(
    val id: Long,
    val notificationDay: Long,
    val name: String,
    val isIncome: Boolean,
    val value: Long,
    val creatorId: Long,
    val creator: String,
    val accountBookName: String,
)

fun FinanceSchedule.toData() = FinanceScheduleData(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)

fun FinanceScheduleData.toDomain() = FinanceSchedule(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)