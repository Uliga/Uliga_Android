package com.uliga.app.view.accountBook.selection

import com.uliga.domain.model.accountBook.AccountBooks
import javax.annotation.concurrent.Immutable


@Immutable
data class AccountBookSelectionUiState(
    val isLoading: Boolean = false,
    val accountBooks: AccountBooks? = null,
) {
    companion object {
        fun empty() = AccountBookSelectionUiState(
            isLoading = false,
            accountBooks = null
        )
    }
}