package com.uliga.data.model.accountBook.transaction

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionResponse
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionResponseData(
    val accountBookId: Long,
    val recordInfo: AccountBookTransactionInfoData?,
    val incomeInfo: AccountBookTransactionInfoData?
)

fun AccountBookTransactionResponse.toData() = AccountBookTransactionResponseData(
    accountBookId = accountBookId,
    recordInfo = recordInfo?.toData(),
    incomeInfo = incomeInfo?.toData()
)

fun AccountBookTransactionResponseData.toDomain() = AccountBookTransactionResponse(
    accountBookId = accountBookId,
    recordInfo = recordInfo?.toDomain(),
    incomeInfo = incomeInfo?.toDomain()
)