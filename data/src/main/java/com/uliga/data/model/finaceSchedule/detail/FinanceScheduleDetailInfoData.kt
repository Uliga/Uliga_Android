package com.uliga.data.model.finaceSchedule.detail

import com.uliga.domain.model.finaceSchedule.detail.FinanceScheduleDetailInfo
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailInfoData(
    val id: Long,
    val notificationDay: Long,
    val name: String,
    val isIncome: Boolean,
    val value: Long,
    val creatorId: Long,
    val creator: String,
    val accountBookName: String
)

fun FinanceScheduleDetailInfo.toData() = FinanceScheduleDetailInfoData(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)

fun FinanceScheduleDetailInfoData.toDomain() = FinanceScheduleDetailInfo(
    id = id,
    notificationDay = notificationDay,
    name = name,
    isIncome = isIncome,
    value = value,
    creatorId = creatorId,
    creator = creator,
    accountBookName = accountBookName
)