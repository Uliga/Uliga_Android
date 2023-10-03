package com.uliga.data_remote.model.userAuth

import com.uliga.data.model.userAuth.UserAuthDataExistedData
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