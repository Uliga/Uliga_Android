package com.uliga.app.view.accountBook.input

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBookInputUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null
) {
    companion object {
        fun empty() = AccountBookInputUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null
        )
    }
}