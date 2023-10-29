package com.uliga.data_remote


object StatusCode {
    const val BadRequest = 400
    const val UnAuthorized = 401
    const val Forbidden = 403
    const val NotFound = 404
    const val Conflict = 409
    const val InternalServer = 500
    const val BadGateway = 502
}

fun Int.isNotSuccess(): Boolean =
    this !in 200 .. 299

fun Int.isSuccess(): Boolean =
    this in 200 .. 299