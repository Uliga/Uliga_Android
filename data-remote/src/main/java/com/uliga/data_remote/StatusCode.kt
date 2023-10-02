package com.uliga.data_remote



fun Int.isNotSuccess(): Boolean =
    this !in 200 .. 299

fun Int.isSuccess(): Boolean =
    this in 200 .. 299