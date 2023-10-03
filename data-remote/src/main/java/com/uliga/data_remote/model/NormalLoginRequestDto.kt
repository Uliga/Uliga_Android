package com.uliga.data_remote.model

import com.uliga.data.model.NormalLoginRequestData
import kotlinx.serialization.Serializable


@Serializable
data class NormalLoginRequestDto(
    val email: String,
    val password: String
)

fun NormalLoginRequestDto.toData() = NormalLoginRequestData(
    email = email,
    password = password
)

fun NormalLoginRequestData.toDto() = NormalLoginRequestDto(
    email = email,
    password = password
)