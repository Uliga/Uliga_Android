package com.uliga.app.view.finance

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.uliga.app.view.main.MainUiEvent
import com.uliga.app.view.main.MainUiState

@Composable
fun FinanceScreen(
    navController: NavController,
    data: MainUiState,
    onEvent: (MainUiEvent) -> Unit
) {

    val context = LocalContext.current

    Text(text = "finance")

}