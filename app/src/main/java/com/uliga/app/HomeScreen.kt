package com.uliga.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    data: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
) {

    val context = LocalContext.current


}