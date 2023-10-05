package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookGenerationResponseData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookGenerationResponseDto(
    val id: Long,
    val name: String,
    val isPrivate: Boolean,
    val relationShip: String,
)

fun AccountBookGenerationResponseDto.toData() = AccountBookGenerationResponseData(
    id = id,
    name = name,
    isPrivate = isPrivate,
    relationShip = relationShip
)

fun AccountBookGenerationResponseData.toDto() = AccountBookGenerationResponseDto(
    id = id,
    name = name,
    isPrivate = isPrivate,
    relationShip = relationShip
)