package com.uliga.data.model.userAuth

import com.uliga.domain.model.userAuth.NormalLoginRequest
import kotlinx.serialization.Serializable


@Serializable
data class NormalLoginRequestData(
    val email: String,
    val password: String
)

fun NormalLoginRequestData.toDomain() = NormalLoginRequest(
    email = email,
    password = password
)

fun NormalLoginRequest.toData() = NormalLoginRequestData(
    email = email,
    password = password
)