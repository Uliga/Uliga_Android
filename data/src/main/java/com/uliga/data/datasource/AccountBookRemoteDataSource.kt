package com.uliga.data.datasource

import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData

interface AccountBookRemoteDataSource {

    suspend fun getAccountBooks(): AccountBooksData

    suspend fun postAccountBook(accountBookGenerationRequestData: AccountBookGenerationRequestData): AccountBookGenerationResponseData

    suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequestData: AccountBookFinanceScheduleRequestData): AccountBookFinanceScheduleResponseData
}