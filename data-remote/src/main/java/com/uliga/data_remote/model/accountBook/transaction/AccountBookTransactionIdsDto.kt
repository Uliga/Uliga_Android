package com.uliga.data_remote.model.accountBook.transaction

import com.uliga.data.model.accountBook.transaction.AccountBookTransactionIdsData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionIdsDto(
    val ids: List<Long>
)

fun AccountBookTransactionIdsData.toDto() = AccountBookTransactionIdsDto(
    ids = ids
)

fun AccountBookTransactionIdsDto.toData() = AccountBookTransactionIdsData(
    ids = ids
)