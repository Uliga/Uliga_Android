package com.uliga.app.view.finance

import androidx.compose.runtime.Immutable

@Immutable
data class FinanceUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
) {
    companion object {
        fun empty() = FinanceUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null
        )
    }
}