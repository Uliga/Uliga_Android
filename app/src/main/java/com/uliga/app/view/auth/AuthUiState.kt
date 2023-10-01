package com.uliga.app.view.auth

import androidx.compose.runtime.Immutable

@Immutable
data class AuthUiState(
    val isLoading: Boolean
) {

    companion object {
        fun empty() = AuthUiState(
            isLoading = false
        )
    }
}