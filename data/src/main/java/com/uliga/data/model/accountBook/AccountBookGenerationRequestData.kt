package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import kotlinx.serialization.Serializable


@Serializable
data class AccountBookGenerationRequestData(
    val name: String,
    val categories: List<String>,
    val emails: List<String>,
    val relationship: String
)

fun AccountBookGenerationRequestData.toDomain() = AccountBookGenerationRequest(
    name = name,
    categories = categories,
    emails = emails,
    relationship = relationship
)

fun AccountBookGenerationRequest.toData() = AccountBookGenerationRequestData(
    name = name,
    categories = categories,
    emails = emails,
    relationship = relationship
)