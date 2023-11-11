package com.uliga.app.view.accountBook.generation

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.accountBook.AccountBooks

@Immutable
data class AccountBookGenerationUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val accountBooks: AccountBooks? = null,
) {
    companion object {
        fun empty() = AccountBookGenerationUiState(
            isLoading = false,
            exception = null,
            accountBooks = null
        )
    }
}