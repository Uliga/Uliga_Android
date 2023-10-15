package com.uliga.app.view.finance

import androidx.compose.runtime.Immutable

@Immutable
data class FinanceUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
) {
    companion object {
        fun empty() = FinanceUiState(
            isLoading = false,
            exception = null
        )
    }
}