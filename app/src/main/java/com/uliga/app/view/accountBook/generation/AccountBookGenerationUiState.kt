package com.uliga.app.view.accountBook.generation

import com.uliga.domain.model.accountBook.AccountBooks
import javax.annotation.concurrent.Immutable


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