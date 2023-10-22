package com.uliga.app.view.profile

import javax.annotation.concurrent.Immutable

@Immutable
data class ProfileUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
) {
    companion object {
        fun empty() = ProfileUiState(
            isLoading = false,
            exception = null
        )
    }
}