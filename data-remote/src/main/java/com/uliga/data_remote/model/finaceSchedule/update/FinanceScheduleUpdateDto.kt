package com.uliga.data_remote.model.finaceSchedule.update

import com.uliga.data.model.finaceSchedule.update.FinanceScheduleUpdateData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateDto(
    val id: Long,
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: FinanceScheduleUpdateAssignmentDto
)

fun FinanceScheduleUpdateData.toDto() = FinanceScheduleUpdateDto(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.toDto()
)

fun FinanceScheduleUpdateDto.toData() = FinanceScheduleUpdateData(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.toData()
)