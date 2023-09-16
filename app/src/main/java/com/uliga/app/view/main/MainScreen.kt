package com.uliga.app.view.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val navController: NavHostController = rememberNavController()
    val data by viewModel.uiState.collectAsStateWithLifecycle()
    val showLoading by viewModel.showLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.error.collectAsStateWithLifecycle(null)

    MainNavigationComponent(
        navController = navController,
        viewModel = viewModel
    )


}