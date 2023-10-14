package com.uliga.data.model.accountBook.financeSchedule

import com.uliga.data.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResultData
import com.uliga.data.model.accountBook.financeSchedule.common.toData
import com.uliga.data.model.accountBook.financeSchedule.common.toDomain
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookFinanceScheduleRequestData(
    val id: Long,
    val schedules: List<AccountBookFinanceScheduleResultData>,
)

fun AccountBookFinanceScheduleRequest.toData() = AccountBookFinanceScheduleRequestData(
    id = id,
    schedules = schedules.map { it.toData() }
)

fun AccountBookFinanceScheduleRequestData.toDomain() = AccountBookFinanceScheduleRequest(
    id = id,
    schedules = schedules.map { it.toDomain() }
)
