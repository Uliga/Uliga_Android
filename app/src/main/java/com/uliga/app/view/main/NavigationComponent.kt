package com.uliga.app.view.main

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.uliga.app.R
import com.uliga.app.view.analyze.AnalyzeScreen
import com.uliga.app.view.finance.FinanceScreen
import com.uliga.app.view.home.HomeScreen
import com.uliga.app.view.profile.MyScreen

enum class MainRoute(
    @StringRes val title: Int,
    val route: String
) {
    HOME(
        title = R.string.home,
        route = "home"
    ),
    FINANCE(
        title = R.string.finance,
        route = "finance"
    ),
    ANALYZE(
        title = R.string.analyze,
        route = "analyze"
    ),
    PROFILE(
        title = R.string.profile,
        route = "profile"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigationComponent(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val data by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                MainRoute.values().forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        },
                        label = { Text(text = stringResource(id = screen.title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainRoute.HOME.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MainRoute.HOME.route) {
                HomeScreen(
                    navController = navController,
                    data = data,
                    onEvent = viewModel::updateEvent,
                )
            }
            composable(MainRoute.FINANCE.route) {
                FinanceScreen(
                    navController = navController,
                    data = data,
                    onEvent = viewModel::updateEvent,
                )
            }
            composable(MainRoute.ANALYZE.route) {
                AnalyzeScreen(
                    navController = navController,
                    data = data,
                    onEvent = viewModel::updateEvent,
                )
            }
            composable(MainRoute.PROFILE.route) {
                MyScreen(
                    navController = navController,
                    data = data,
                    onEvent = viewModel::updateEvent,
                )
            }
        }
    }

}



