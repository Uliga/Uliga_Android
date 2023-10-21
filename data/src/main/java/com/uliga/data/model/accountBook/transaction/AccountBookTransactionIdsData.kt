package com.uliga.data.model.accountBook.transaction

import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookTransactionIdsData(
    val ids: List<Long>
)

fun AccountBookTransactionIds.toData() = AccountBookTransactionIdsData(
    ids = ids
)

fun AccountBookTransactionIdsData.toDomain() = AccountBookTransactionIds(
    ids = ids
)