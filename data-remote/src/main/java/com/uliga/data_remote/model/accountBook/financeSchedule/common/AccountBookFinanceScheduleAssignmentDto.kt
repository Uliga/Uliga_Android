package com.uliga.data_remote.model.accountBook.financeSchedule.common

import com.uliga.data.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignmentData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleAssignmentDto(
    val id: Long,
    val username: String,
    val value: Long
)

fun AccountBookFinanceScheduleAssignmentData.toDto() = AccountBookFinanceScheduleAssignmentDto(
    id = id,
    username = username,
    value = value
)

fun AccountBookFinanceScheduleAssignmentDto.toData() = AccountBookFinanceScheduleAssignmentData(
    id = id,
    username = username,
    value = value
)