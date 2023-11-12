package com.uliga.app.view.auth

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uliga.app.base.UligaActivity
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.view.auth.login.LoginScreen
import com.uliga.app.view.auth.signup.NormalSignUpScreen
import com.uliga.app.view.auth.signup.SocialSignupScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity(), UligaActivity {

    private val viewModel: AuthViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            UligaApplicationTheme {

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

                    composable(AuthRoute.NORMAL_SIGNUP.route) {
                        NormalSignUpScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }

                    composable(
                        route = AuthRoute.SOCIAL_SIGNUP.route + "/{email}/{name}",
                        arguments = listOf(
                            navArgument("email") { type = NavType.StringType },
                            navArgument("name") { type = NavType.StringType }
                        )
                    ) {
                        val paramEmail = it.arguments?.getString("email") ?: ""
                        val paramName = it.arguments?.getString("name") ?: ""

                        SocialSignupScreen(
                            viewModel = viewModel,
                            paramEmail = paramEmail,
                            paramName = paramName
                        )
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
        NORMAL_SIGNUP(
            route = "회원가입"
        ),
        SOCIAL_SIGNUP(
            route = "소셜 회원가입"
        )
    }
}