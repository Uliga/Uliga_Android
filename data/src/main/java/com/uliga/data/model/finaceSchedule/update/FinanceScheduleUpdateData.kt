package com.uliga.data.model.finaceSchedule.update

import com.uliga.domain.model.finaceSchedule.update.FinanceScheduleUpdate
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateData(
    val id: Long,
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: FinanceScheduleUpdateAssignmentData
)

fun FinanceScheduleUpdate.toData() = FinanceScheduleUpdateData(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.toData()
)

fun FinanceScheduleUpdateData.toDomain() = FinanceScheduleUpdate(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.toDomain()
)

