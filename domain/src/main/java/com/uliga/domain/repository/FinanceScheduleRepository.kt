package com.uliga.domain.repository

import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.model.financeSchedule.detail.FinanceScheduleDetail
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate

interface FinanceScheduleRepository {

    suspend fun getFinanceSchedule(): Result<FinanceSchedules>

    suspend fun patchFinanceSchedule(financeScheduleUpdate: FinanceScheduleUpdate): Result<FinanceScheduleUpdate>

    suspend fun getFinanceScheduleDetail(id: Long): Result<FinanceScheduleDetail>

    suspend fun deleteFinanceScheduleDetail(id: Long): Result<String>
}