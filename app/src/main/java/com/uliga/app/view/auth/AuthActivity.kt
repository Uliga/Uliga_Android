package com.uliga.app.view.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uliga.app.base.ComposeActivity
import com.uliga.app.ui.theme.MyApplicationTheme
import com.uliga.app.view.auth.login.LoginScreen
import com.uliga.app.view.auth.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComposeActivity<AuthViewModel, AuthUiEvent>() {

    override val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val navController = rememberNavController()
                val data by viewModel.uiState.collectAsStateWithLifecycle()
                val showLoading by viewModel.showLoading.collectAsStateWithLifecycle()
                val errorMessage by viewModel.error.collectAsStateWithLifecycle(null)

                NavHost(
                    navController = navController,
                    startDestination = AuthRoute.LOGIN.route,
                ) {
                    composable(AuthRoute.LOGIN.route) {
                        LoginScreen(
                            navController = navController,
                            data = data,
                            onEvent = viewModel::updateEvent
                        )
                    }

                    composable(AuthRoute.SIGNUP.route) {
                        SignUpScreen(
                            navController = navController,
                            data = data,
                            onEvent = viewModel::updateEvent
                        )
                    }
                }
            }
        }
    }
}

enum class AuthRoute(
    val route: String
) {
    LOGIN(
        route = "로그인"
    ),
    SIGNUP(
        route = "회원가입"
    )
}