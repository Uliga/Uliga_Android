package com.uliga.app

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.uliga.app.utils.TestTags
import com.uliga.app.view.home.HomeScreen
import com.uliga.app.view.main.MainActivity
import com.uliga.app.view.main.MainUiState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {

            HomeScreen()
        }
    }

    @Test
    fun clickInvitationButton() {
        with(composeRule) {
            onNodeWithTag(TestTags.INVITATION).performClick()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.NEW_INVITATION).assertIsDisplayed()

            Thread.sleep(1000L)

            backButtonPressed()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BUDGET_SETTING).performClick()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BUDGET_CURRENT_DATE).assertIsDisplayed()
            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_BUDGET).assertIsDisplayed()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_BUDGET).apply {
                performClick()
                performTextInput("10000")
                assertTextContains("10000")
            }

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BUTTON_BUDGET_SETTING).performClick()



            onNodeWithTag(TestTags.INVITATION).assertIsDisplayed()

            Thread.sleep(2000L)


        }
    }

    fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.backButtonPressed() {
        activityRule.scenario.onActivity {
            it.onBackPressedDispatcher.onBackPressed()
        }

    }


}