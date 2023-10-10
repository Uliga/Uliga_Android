package com.uliga.data_remote.model.financeSchedule.detail

import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailAssignmentData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailAssignmentDto(
    val id: Long,
    val username: String,
    val value: Long
)

fun FinanceScheduleDetailAssignmentData.toDto() = FinanceScheduleDetailAssignmentDto(
    id = id,
    username = username,
    value = value
)

fun FinanceScheduleDetailAssignmentDto.toData() = FinanceScheduleDetailAssignmentData(
    id = id,
    username = username,
    value = value
)