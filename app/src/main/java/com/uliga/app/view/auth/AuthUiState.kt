package com.uliga.app.view.auth

import androidx.compose.runtime.Immutable

@Immutable
data class AuthUiState(
    val isLoading: Boolean,
    val exception: Exception? = null,
) {

    companion object {
        fun empty() = AuthUiState(
            isLoading = false,
            exception = null,
        )
    }
}