package com.uliga.data_remote.datasource

import com.uliga.data.datasource.FinanceScheduleRemoteDataSource
import com.uliga.data.model.financeSchedule.common.FinanceScheduleData
import com.uliga.data.model.financeSchedule.detail.FinanceScheduleDetailData
import com.uliga.data.model.financeSchedule.update.FinanceScheduleUpdateData
import com.uliga.data_remote.Path
import com.uliga.data_remote.model.financeSchedule.common.FinanceScheduleDto
import com.uliga.data_remote.model.financeSchedule.common.toData
import com.uliga.data_remote.model.financeSchedule.detail.FinanceScheduleDetailDto
import com.uliga.data_remote.model.financeSchedule.detail.toData
import com.uliga.data_remote.model.financeSchedule.update.FinanceScheduleUpdateDto
import com.uliga.data_remote.model.financeSchedule.update.toData
import com.uliga.data_remote.model.financeSchedule.update.toDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.path
import javax.inject.Inject

class FinanceScheduleRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : FinanceScheduleRemoteDataSource {
    override suspend fun getFinanceSchedule(): FinanceScheduleData {
        return client.get {
            url.path(Path.SCHEDULE)
        }.body<FinanceScheduleDto>().toData()
    }

    override suspend fun patchFinanceSchedule(financeScheduleUpdateData: FinanceScheduleUpdateData): FinanceScheduleUpdateData {
        return client.patch {
            url.path(Path.SCHEDULE)
            setBody(financeScheduleUpdateData.toDto())
        }.body<FinanceScheduleUpdateDto>().toData()
    }

    override suspend fun getFinanceScheduleDetail(id: Long): FinanceScheduleDetailData {
        return client.get {
            url.path(Path.SCHEDULE, id.toString())
        }.body<FinanceScheduleDetailDto>().toData()
    }

    override suspend fun deleteFinanceScheduleDetail(id: Long): String {
        return client.delete {
            url.path(Path.SCHEDULE, id.toString())
        }.body()
    }
}