package com.uliga.data_remote.model.accountBook.financeSchedule.common

import com.uliga.data.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResultData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleResultDto(
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: List<AccountBookFinanceScheduleAssignmentDto>,
)

fun AccountBookFinanceScheduleResultData.toDto() = AccountBookFinanceScheduleResultDto(
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.map { it.toDto() }
)

fun AccountBookFinanceScheduleResultDto.toData() = AccountBookFinanceScheduleResultData(
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.map { it.toData() }
)

