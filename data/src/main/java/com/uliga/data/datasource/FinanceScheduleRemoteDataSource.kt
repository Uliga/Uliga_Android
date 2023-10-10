package com.uliga.data.datasource

interface FinanceScheduleRemoteDataSource {

    suspend fun getFinanceSchedule()
}