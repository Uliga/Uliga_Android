package com.uliga.data_remote.model.accountBook.financeSchedule

import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data_remote.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResultDto
import com.uliga.data_remote.model.accountBook.financeSchedule.common.toData
import com.uliga.data_remote.model.accountBook.financeSchedule.common.toDto
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookFinanceScheduleRequestDto(
    val id: Long,
    val schedules: List<AccountBookFinanceScheduleResultDto>,
)

fun AccountBookFinanceScheduleRequestData.toDto() = AccountBookFinanceScheduleRequestDto(
    id = id,
    schedules = schedules.map { it.toDto() }
)

fun AccountBookFinanceScheduleRequestDto.toData() = AccountBookFinanceScheduleRequestData(
    id = id,
    schedules = schedules.map { it.toData() }
)