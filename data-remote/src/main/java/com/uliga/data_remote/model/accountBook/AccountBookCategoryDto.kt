package com.uliga.data_remote.model.accountBook

import com.uliga.data.model.accountBook.AccountBookCategoryData
import kotlinx.serialization.Serializable

@Serializable
data class AccountBookCategoryDto(
    val id: Long,
    val value: String,
    val label: String,
)

fun AccountBookCategoryDto.toData() = AccountBookCategoryData(
    id = id,
    value = value,
    label = label
)

fun AccountBookCategoryData.toDto() = AccountBookCategoryDto(
    id = id,
    value = value,
    label = label
)
