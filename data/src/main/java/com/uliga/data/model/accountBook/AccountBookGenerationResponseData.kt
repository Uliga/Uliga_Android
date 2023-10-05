package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookGenerationResponse
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookGenerationResponseData(
    val id: Long,
    val name: String,
    val isPrivate: Boolean,
    val relationShip: String,
)

fun AccountBookGenerationResponseData.toDomain() = AccountBookGenerationResponse(
    id = id,
    name = name,
    isPrivate = isPrivate,
    relationShip = relationShip
)

fun AccountBookGenerationResponse.toData() = AccountBookGenerationResponseData(
    id = id,
    name = name,
    isPrivate = isPrivate,
    relationShip = relationShip
)