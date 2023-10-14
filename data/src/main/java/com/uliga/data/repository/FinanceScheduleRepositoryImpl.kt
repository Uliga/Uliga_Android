package com.uliga.data.repository

import com.uliga.data.datasource.FinanceScheduleRemoteDataSource
import com.uliga.data.model.financeSchedule.common.toDomain
import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailData
import com.uliga.data.model.financeSchedule.detail.toDomain
import com.uliga.data.model.financeSchedule.update.toData
import com.uliga.data.model.financeSchedule.update.toDomain
import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.model.financeSchedule.detail.FinanceScheduleDetail
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.repository.FinanceScheduleRepository
import javax.inject.Inject

class FinanceScheduleRepositoryImpl @Inject constructor(
    private val remote: FinanceScheduleRemoteDataSource
) : FinanceScheduleRepository {
    override suspend fun getFinanceSchedule(): Result<FinanceSchedules> {
        return runCatching {
            remote.getFinanceSchedule().toDomain()
        }
    }

    override suspend fun patchFinanceSchedule(financeScheduleUpdate: FinanceScheduleUpdate): Result<FinanceScheduleUpdate> {
        return runCatching {
            remote.patchFinanceSchedule(financeScheduleUpdate.toData()).toDomain()
        }
    }

    override suspend fun getFinanceScheduleDetail(id: Long): Result<FinanceScheduleDetail> {
        return runCatching {
            remote.getFinanceScheduleDetail(id).toDomain()
        }
    }

    override suspend fun deleteFinanceScheduleDetail(id: Long): Result<String> {
        return runCatching {
            remote.deleteFinanceScheduleDetail(id)
        }
    }
}