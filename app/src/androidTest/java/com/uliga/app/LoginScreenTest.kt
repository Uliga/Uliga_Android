package com.uliga.app

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.utils.TestTags
import com.uliga.app.view.auth.AuthActivity
import com.uliga.app.view.auth.AuthViewModel
import com.uliga.app.view.auth.login.LoginScreen
import com.uliga.app.view.home.HomeScreen
import com.uliga.app.view.home.HomeViewModel
import com.uliga.app.view.main.MainActivity
import com.uliga.app.view.main.MainRoute
import com.uliga.app.view.main.MainUiState
import com.uliga.app.view.main.MainViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<AuthActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {

            val navController = rememberNavController()
            UligaApplicationTheme {
                LoginScreen(
                    navController = navController,
                )
            }
        }
    }

    @Test
    fun clickGoogleLoginButton() {
        composeRule.onNodeWithTag(TestTags.GOOGLE_LOGIN_BUTTON).performClick()

        composeRule.activityRule.scenario.onActivity {
            it.startActivity(Intent(it, MainActivity::class.java))
            it.finish()
            it.setContent {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MainRoute.HOME.route,
                ) {
                    composable(MainRoute.HOME.route) {
                        HomeScreen()
                    }
                }
            }

            Thread.sleep(3000L)
        }
    }
}