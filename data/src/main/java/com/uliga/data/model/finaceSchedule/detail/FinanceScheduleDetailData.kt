package com.uliga.data.model.finaceSchedule.detail

import com.uliga.domain.model.finaceSchedule.detail.FinanceScheduleDetail
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailData(
    val info: FinanceScheduleDetailInfoData,
    val assignments: List<FinanceScheduleDetailAssignmentData>,
)

fun FinanceScheduleDetail.toData() = FinanceScheduleDetailData(
    info = info.toData(),
    assignments = assignments.map { it.toData() }
)

fun FinanceScheduleDetailData.toDomain() = FinanceScheduleDetail(
    info = info.toDomain(),
    assignments = assignments.map { it.toDomain() }
)