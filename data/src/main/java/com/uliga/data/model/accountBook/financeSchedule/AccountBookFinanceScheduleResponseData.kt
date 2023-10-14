package com.uliga.data.model.accountBook.financeSchedule

import com.uliga.data.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResultData
import com.uliga.data.model.accountBook.financeSchedule.common.toData
import com.uliga.data.model.accountBook.financeSchedule.common.toDomain
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponse
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookFinanceScheduleResponseData(
    val result: AccountBookFinanceScheduleResultData
)

fun AccountBookFinanceScheduleResponse.toData() = AccountBookFinanceScheduleResponseData(
    result = result.toData()
)

fun AccountBookFinanceScheduleResponseData.toDomain() = AccountBookFinanceScheduleResponse(
    result = result.toDomain()
)
