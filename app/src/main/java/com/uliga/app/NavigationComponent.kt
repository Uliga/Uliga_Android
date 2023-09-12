package com.uliga.app

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class MainRoute(
    @StringRes val title: Int? = null,
    val route: String
) {
    HOME(route = "home"),
}

@Composable
fun MainNavigationComponent(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val data by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = MainRoute.HOME.route
    ) {
        composable(MainRoute.HOME.route) {
            HomeScreen(
                navController = navController,
                data = data,
                onEvent = viewModel::updateEvent,
            )
        }
    }
}