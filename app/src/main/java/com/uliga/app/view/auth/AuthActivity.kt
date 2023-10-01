package com.uliga.app.view.auth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uliga.app.base.ComposeActivity
import com.uliga.app.ui.theme.MyApplicationTheme
import com.uliga.app.view.auth.login.LoginScreen
import com.uliga.app.view.auth.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = AuthRoute.LOGIN.route,
                ) {
                    composable(AuthRoute.LOGIN.route) {
                        LoginScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }

                    composable(AuthRoute.SIGNUP.route) {
                        SignUpScreen(
                            navController = navController,
                            viewModel = viewModel
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