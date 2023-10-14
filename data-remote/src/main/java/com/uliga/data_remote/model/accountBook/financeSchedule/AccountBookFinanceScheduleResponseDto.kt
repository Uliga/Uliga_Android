package com.uliga.data_remote.model.accountBook.financeSchedule

import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data_remote.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResultDto
import com.uliga.data_remote.model.accountBook.financeSchedule.common.toData
import com.uliga.data_remote.model.accountBook.financeSchedule.common.toDto
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleResponseDto(
    val result: AccountBookFinanceScheduleResultDto
)

fun AccountBookFinanceScheduleResponseData.toDto() = AccountBookFinanceScheduleResponseDto(
    result = result.toDto()
)

fun AccountBookFinanceScheduleResponseDto.toData() = AccountBookFinanceScheduleResponseData(
    result = result.toData()
)
