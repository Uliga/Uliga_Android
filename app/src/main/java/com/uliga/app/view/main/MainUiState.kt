package com.uliga.app.view.main

import com.uliga.app.base.ComposeViewModel

data class MainUiState(
    val userToken: String? = null,
    val showAccountBookSelectionBottomSheet: Boolean? = null,
) : ComposeViewModel.UiState