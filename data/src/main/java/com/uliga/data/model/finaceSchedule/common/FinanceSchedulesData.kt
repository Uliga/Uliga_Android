package com.uliga.data.model.finaceSchedule.common

import com.uliga.domain.model.finaceSchedule.common.FinanceSchedules
import kotlinx.serialization.Serializable


@Serializable
data class FinanceSchedulesData(
    val schedules: List<FinanceScheduleData>,
)

fun FinanceSchedules.toData() = FinanceSchedulesData(
    schedules = schedules.map { it.toData() }
)

fun FinanceSchedulesData.toDomain() = FinanceSchedules(
    schedules = schedules.map { it.toDomain() }
)