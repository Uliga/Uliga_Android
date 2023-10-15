package com.uliga.data.model.accountBook.transaction

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionResponse
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionResponseData(
    val accountBookId: Long,
    val recordInfo: AccountBookTransactionRecordInfoData
)

fun AccountBookTransactionResponse.toData() = AccountBookTransactionResponseData(
    accountBookId = accountBookId,
    recordInfo = recordInfo.toData()
)

fun AccountBookTransactionResponseData.toDomain() = AccountBookTransactionResponse(
    accountBookId = accountBookId,
    recordInfo = recordInfo.toDomain()
)