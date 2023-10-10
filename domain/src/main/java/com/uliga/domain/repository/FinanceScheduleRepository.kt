package com.uliga.domain.repository

import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate

interface FinanceScheduleRepository {

    suspend fun getFinanceSchedule(): Result<FinanceSchedule>

    suspend fun patchFinanceSchedule(financeScheduleUpdate: FinanceScheduleUpdate): Result<FinanceScheduleUpdate>

    suspend fun getFinanceScheduleDetail(id: Long): Result<FinanceScheduleUpdate>

    suspend fun deleteFinanceScheduleDetail(id: Long): Result<String>
}