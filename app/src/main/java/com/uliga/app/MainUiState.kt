package com.uliga.app

import com.uliga.app.base.ComposeViewModel

data class MainUiState(
    val userToken: String? = null,
) : ComposeViewModel.UiState