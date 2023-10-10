package com.uliga.data_remote.model.finaceSchedule.common

import com.uliga.data.model.finaceSchedule.common.FinanceScheduleData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDto(
    val id: Long,
    val notificationDay: Long,
    val name: String,
    val isIncome: Boolean,
    val value: Long,
    val creatorId: Long,
    val creator: String,
    val accountBookName: String,
)

fun FinanceScheduleData.toDto() = FinanceScheduleDto(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)

fun FinanceScheduleDto.toData() = FinanceScheduleData(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)