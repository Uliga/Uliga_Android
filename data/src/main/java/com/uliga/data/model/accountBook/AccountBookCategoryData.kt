package com.uliga.data.model.accountBook

import com.uliga.domain.model.accountBook.AccountBookCategory
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookCategoryData(
    val id: Long,
    val value: String,
    val label: String,
)

fun AccountBookCategoryData.toDomain() = AccountBookCategory(
    id = id,
    value = value,
    label = label
)

fun AccountBookCategory.toData() = AccountBookCategoryData(
    id = id,
    value = value,
    label = label
)