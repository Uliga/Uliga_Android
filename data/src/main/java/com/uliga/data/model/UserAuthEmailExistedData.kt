package com.uliga.data.model

import com.uliga.domain.model.UserAuthEmailExisted
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthEmailExistedData(
    val exists: Boolean?
)

fun UserAuthEmailExistedData.toDomain() = UserAuthEmailExisted(
    exists = exists
)

fun UserAuthEmailExisted.toData() = UserAuthEmailExistedData(
    exists = exists
)