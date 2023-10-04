package com.uliga.data_remote.model.userAuth

import kotlinx.serialization.Serializable


@Serializable
data class ReissueResponseDto(
    val accessToken: String,
    val grantType: String,
    val accessTokenExpiresIn: Long,
)
