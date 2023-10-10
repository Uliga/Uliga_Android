package com.uliga.data.model.financeSchedule.detail

import com.uliga.domain.model.financeSchedule.detail.FinanceScheduleDetailAssignment
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleDetailAssignmentData(
    val id: Long,
    val username: String,
    val value: Long
)


fun FinanceScheduleDetailAssignment.toData() = FinanceScheduleDetailAssignmentData(
    id = id,
    username = username,
    value = value
)

fun FinanceScheduleDetailAssignmentData.toDomain() = FinanceScheduleDetailAssignment(
    id = id,
    username = username,
    value = value
)