package com.uliga.app.view.home

import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>?= null,
) {

    companion object {
        fun empty() = HomeUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null
        )
    }
}