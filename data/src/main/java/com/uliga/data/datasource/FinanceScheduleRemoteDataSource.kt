package com.uliga.data.datasource

import com.uliga.data.model.financeSchedule.common.FinanceSchedulesData
import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailData
import com.uliga.data.model.financeSchedule.update.FinanceScheduleUpdateData

interface FinanceScheduleRemoteDataSource {

    suspend fun getFinanceSchedule(): FinanceSchedulesData

    suspend fun patchFinanceSchedule(financeScheduleUpdateData: FinanceScheduleUpdateData): FinanceScheduleUpdateData

    suspend fun getFinanceScheduleDetail(id: Long): FinanceScheduleDetailData

    suspend fun deleteFinanceScheduleDetail(id: Long): String
}