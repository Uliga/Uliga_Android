package com.uliga.data_remote.model

import com.uliga.data.model.UserAuthEmailExistedData
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthEmailExistedDto(
    val exists: Boolean?
)

fun UserAuthEmailExistedDto.toData() = UserAuthEmailExistedData(
    exists = exists
)

fun UserAuthEmailExistedData.toDto() = UserAuthEmailExistedDto(
    exists = exists
)