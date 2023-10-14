package com.uliga.data.model.accountBook.financeSchedule.common

import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignment
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleAssignmentData(
    val id: Long,
    val username: String,
    val value: Long
)

fun AccountBookFinanceScheduleAssignment.toData() = AccountBookFinanceScheduleAssignmentData(
    id = id,
    username = username,
    value = value
)

fun AccountBookFinanceScheduleAssignmentData.toDomain() = AccountBookFinanceScheduleAssignment(
    id = id,
    username = username,
    value = value
)