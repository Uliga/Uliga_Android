package com.uliga.data_remote

import kotlinx.serialization.Serializable

@Serializable
internal data class ExceptionBody(
    val errorCode: Int,
    val message: String,
)

sealed class UligaException(open val code: String) : Exception(code)
class UligaResponseException(
    val errorCode: Int,
    override val message: String
) : UligaException(message)

internal fun ExceptionBody.throwing(): Nothing {
    throw UligaResponseException(
        errorCode = errorCode,
        message = message
    )
}