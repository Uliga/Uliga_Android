package com.uliga.app.view.accountBook.generation

import javax.annotation.concurrent.Immutable


@Immutable
data class AccountBookGenerationUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
) {
    companion object {
        fun empty() = AccountBookGenerationUiState(
            isLoading = false,
            exception = null,
        )
    }
}