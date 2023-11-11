package com.uliga.app.view.auth

import androidx.compose.runtime.Immutable

@Immutable
data class AuthUiState(
    val isLoading: Boolean = false,
    val isNickNameExisted: Boolean? = null,
    val isPrivacyChecked: Boolean = false
) {

    companion object {
        fun empty() = AuthUiState(
            isLoading = false,
            isNickNameExisted = null,
            isPrivacyChecked = false
        )
    }
}