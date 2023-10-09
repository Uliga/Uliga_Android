package com.uliga.app.view.main

data class MainUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val userToken: String? = null,
    val showAccountBookSelectionBottomSheet: Boolean? = null,
) {
    companion object {
        fun empty() = MainUiState(
            isLoading = false,
            exception = null,
            userToken = null,
            showAccountBookSelectionBottomSheet = null
        )
    }
}