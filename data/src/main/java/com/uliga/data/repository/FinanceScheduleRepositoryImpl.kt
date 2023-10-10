package com.uliga.data.repository

import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.repository.FinanceScheduleRepository
import javax.inject.Inject

class FinanceScheduleRepositoryImpl @Inject constructor(
    private val financeScheduleRepository: FinanceScheduleRepository
) : FinanceScheduleRepository {
    override suspend fun getFinanceSchedule(): Result<FinanceSchedule> {
        return financeScheduleRepository.getFinanceSchedule()
    }

    override suspend fun patchFinanceSchedule(financeScheduleUpdate: FinanceScheduleUpdate): Result<FinanceScheduleUpdate> {
        return financeScheduleRepository.patchFinanceSchedule(financeScheduleUpdate)
    }

    override suspend fun getFinanceScheduleDetail(id: Long): Result<FinanceScheduleUpdate> {
        return financeScheduleRepository.getFinanceScheduleDetail(id)
    }

    override suspend fun deleteFinanceScheduleDetail(id: Long): Result<String> {
        return financeScheduleRepository.deleteFinanceScheduleDetail(id)
    }
}