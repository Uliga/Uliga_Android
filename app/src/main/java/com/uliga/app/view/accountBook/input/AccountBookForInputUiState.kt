package com.uliga.app.view.accountBook.input

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBookForInputUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null
) {
    companion object {
        fun empty() = AccountBookForInputUiState(
            isLoading = false,
            exception = null,
        )
    }
}