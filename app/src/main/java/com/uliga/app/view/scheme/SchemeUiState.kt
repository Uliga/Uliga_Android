package com.uliga.app.view.scheme

import androidx.compose.runtime.Immutable

@Immutable
data class SchemeUiState(
    val isLoading: Boolean = false,
    val isLogin: Boolean? = null
) {
    companion object {
        fun empty() = SchemeUiState(
            isLogin = null
        )
    }
}