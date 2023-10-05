package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookGenerationRequestData
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookGenerationRequestDto(
    val name: String,
    val categories: List<String>,
    val emails: List<String>,
    val relationship: String
)

fun AccountBookGenerationRequestDto.toData() = AccountBookGenerationRequestData(
    name = name,
    categories = categories,
    emails = emails,
    relationship = relationship
)

fun AccountBookGenerationRequestData.toDto() = AccountBookGenerationRequestDto(
    name = name,
    categories = categories,
    emails = emails,
    relationship = relationship
)