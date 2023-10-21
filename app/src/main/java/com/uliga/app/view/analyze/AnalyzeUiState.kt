package com.uliga.app.view.analyze

import androidx.compose.runtime.Immutable

@Immutable
data class AnalyzeUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
) {
    companion object {
        fun empty() = AnalyzeUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null,
        )
    }
}