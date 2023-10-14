package com.uliga.data.datasource

import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData

interface AccountBookRemoteDataSource {

    suspend fun getAccountBooks(): AccountBooksData

    suspend fun postAccountBook(accountBookGenerationRequestData: AccountBookGenerationRequestData): AccountBookGenerationResponseData

    suspend fun postFinanceScheduleToAccountBook(accountBookFinanceScheduleRequestData: AccountBookFinanceScheduleRequestData): AccountBookFinanceScheduleResponseData

    suspend fun postAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData

    suspend fun patchAccountBookBudget(accountBookBudgetRequestData: AccountBookBudgetRequestData): AccountBookBudgetResponseData

    suspend fun getAccountBookMonthAsset(
        accountBookId: Long,
        year: Int,
        month: Int
    ): AccountBookAssetData
}