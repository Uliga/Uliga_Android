package com.uliga.data_remote.model.financeSchedule.update

import com.uliga.data.model.financeSchedule.update.FinanceScheduleUpdateData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateDto(
    val id: Long,
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: HashMap<Long, Long>
)

fun FinanceScheduleUpdateData.toDto() = FinanceScheduleUpdateDto(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments
)

fun FinanceScheduleUpdateDto.toData() = FinanceScheduleUpdateData(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments
)