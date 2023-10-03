package com.uliga.app.view.home

import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null
) {

    companion object {
        fun empty() = HomeUiState(
            isLoading = false,
            exception = null
        )
    }
}