package com.uliga.app.view.accountBook.input

import androidx.compose.runtime.Immutable

@Immutable
data class AccountBookInputUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val accountBookId: Long? = null
) {
    companion object {
        fun empty() = AccountBookInputUiState(
            isLoading = false,
            id = null,
            accountBookId = null
        )
    }
}