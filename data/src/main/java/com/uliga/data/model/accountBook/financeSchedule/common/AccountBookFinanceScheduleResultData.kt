package com.uliga.data.model.accountBook.financeSchedule.common

import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleResultData(
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: List<AccountBookFinanceScheduleAssignmentData>,
)

fun AccountBookFinanceScheduleResult.toData() = AccountBookFinanceScheduleResultData(
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.map { it.toData() }
)

fun AccountBookFinanceScheduleResultData.toDomain() = AccountBookFinanceScheduleResult(
    name = name,
    isIncome = isIncome,
    notificationDate = notificationDate,
    value = value,
    assignments = assignments.map { it.toDomain() }
)

