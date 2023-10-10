package com.uliga.data_remote.model.financeSchedule.common

import com.uliga.data.model.financeSchedule.common.FinanceSchedulesData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceSchedulesDto(
    val schedules: List<FinanceScheduleDto>,
)

fun FinanceSchedulesData.toDto() = FinanceSchedulesDto(
    schedules = schedules.map { it.toDto() }
)

fun FinanceSchedulesDto.toData() = FinanceSchedulesData(
    schedules = schedules.map { it.toData() }
)