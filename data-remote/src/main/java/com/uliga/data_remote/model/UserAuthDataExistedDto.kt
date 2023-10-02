package com.uliga.data_remote.model

import com.uliga.data.model.UserAuthDataExistedData
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthDataExistedDto(
    val exists: Boolean?
)

fun UserAuthDataExistedDto.toData() = UserAuthDataExistedData(
    exists = exists
)

fun UserAuthDataExistedData.toDto() = UserAuthDataExistedDto(
    exists = exists
)