package com.uliga.data.model.financeSchedule.update

import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateData(
    val id: Long,
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: HashMap<Long, Long>
)

fun FinanceScheduleUpdate.toData() = FinanceScheduleUpdateData(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments
)

fun FinanceScheduleUpdateData.toDomain() = FinanceScheduleUpdate(
    id = id,
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments
)

