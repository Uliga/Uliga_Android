package com.uliga.data.datasource

import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import com.uliga.data.model.accountBook.AccountBooksData
import com.uliga.data.model.accountBook.asset.AccountBookAssetData
import com.uliga.data.model.accountBook.asset.day.AccountBookAssetMonthData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetRequestData
import com.uliga.data.model.accountBook.budget.AccountBookBudgetResponseData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequestData
import com.uliga.data.model.accountBook.financeSchedule.AccountBookFinanceScheduleResponseData
import com.uliga.data.model.accountBook.invitation.AccountBookInvitationReplyData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionRequestData
import com.uliga.data.model.accountBook.transaction.AccountBookTransactionResponseData

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

    suspend fun postAccountBookInvitationReply(accountBookInvitationReplyData: AccountBookInvitationReplyData): AccountBookInvitationReplyData

    suspend fun postAccountBookTransaction(
        transactionType: String,
        accountBookTransactionRequestData: AccountBookTransactionRequestData
    ): AccountBookTransactionResponseData

    suspend fun getAccountBookMonthTransaction(
        id: Long,
        year: Int,
        month: Int
    ): AccountBookAssetMonthData
}