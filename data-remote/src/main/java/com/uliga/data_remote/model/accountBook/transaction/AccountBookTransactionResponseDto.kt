package com.uliga.data_remote.model.accountBook.transaction

import com.uliga.data.model.accountBook.transaction.AccountBookTransactionResponseData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionResponseDto(
    val accountBookId: Long,
    val recordInfo: AccountBookTransactionRecordInfoDto
)

fun AccountBookTransactionResponseData.toDto() = AccountBookTransactionResponseDto(
    accountBookId = accountBookId,
    recordInfo = recordInfo.toDto()
)

fun AccountBookTransactionResponseDto.toData() = AccountBookTransactionResponseData(
    accountBookId = accountBookId,
    recordInfo = recordInfo.toData()
)