package com.uliga.data_remote.model.userAuth

import kotlinx.serialization.Serializable


@Serializable
data class ReissueRequestDto(
    val token: String
)

