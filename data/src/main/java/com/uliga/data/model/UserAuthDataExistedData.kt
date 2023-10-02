package com.uliga.data.model

import com.uliga.domain.model.UserAuthDataExisted
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthDataExistedData(
    val exists: Boolean?
)

fun UserAuthDataExistedData.toDomain() = UserAuthDataExisted(
    exists = exists
)

fun UserAuthDataExisted.toData() = UserAuthDataExistedData(
    exists = exists
)