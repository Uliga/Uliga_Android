package com.uliga.data_remote.model.financeSchedule.detail

import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailDto(
    val info: FinanceScheduleDetailInfoDto,
    val assignments: List<FinanceScheduleDetailAssignmentDto>,
)

fun FinanceScheduleDetailData.toDto() = FinanceScheduleDetailDto(
    info = info.toDto(),
    assignments = assignments.map { it.toDto() }
)

fun FinanceScheduleDetailDto.toData() = FinanceScheduleDetailData(
    info = info.toData(),
    assignments = assignments.map { it.toData() }
)