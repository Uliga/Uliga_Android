package com.uliga.data_remote.model.financeSchedule.detail

import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailInfoData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailInfoDto(
    val id: Long,
    val notificationDay: Long,
    val name: String,
    val isIncome: Boolean,
    val value: Long,
    val creatorId: Long,
    val creator: String,
    val accountBookName: String
)

fun FinanceScheduleDetailInfoData.toDto() = FinanceScheduleDetailInfoDto(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)

fun FinanceScheduleDetailInfoDto.toData() = FinanceScheduleDetailInfoData(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)