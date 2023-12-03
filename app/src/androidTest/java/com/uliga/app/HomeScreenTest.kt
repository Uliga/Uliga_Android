package com.uliga.app

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.uliga.app.utils.TestTags
import com.uliga.app.view.accountBook.generation.AccountBookGenerationActivity
import com.uliga.app.view.accountBook.selection.AccountBookSelectionActivity
import com.uliga.app.view.home.HomeScreen
import com.uliga.app.view.main.MainActivity
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
    fun homeScreen_invitationBottomSheet_test() {

        with(composeRule) {
            onNodeWithTag(TestTags.INVITATION).performClick()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.NEW_INVITATION).assertIsDisplayed()

            Thread.sleep(1000L)

            backButtonPressed()

            Thread.sleep(1000L)

        }
    }

    @Test
    fun homeScreen_budgetSettingBottomSheet_test() {
//        with(composeRule) {
//            onNodeWithTag(TestTags.BUDGET_SETTING).performClick()
//
//            Thread.sleep(1000L)
//
//            onNodeWithTag(TestTags.BUDGET_CURRENT_DATE).assertIsDisplayed()
//
//            Thread.sleep(1000L)
//
//            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_BUDGET)
//                .performClick()
//
//
//            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_BUDGET)
//                .performTextInput(budget)
//
//            Thread.sleep(1000L)
//
//
//            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_BUDGET)
//                .assertTextContains(budget)
//
//
//            onNodeWithTag(TestTags.BUTTON_BUDGET_SETTING).performClick()
//
//            backButtonPressed()
//
//            waitUntil(10000) { true }
//
//
//            onNodeWithTag(TestTags.MONTH_BUDGET_VALUE).assertTextEquals(budget + "원")
//
//        }
    }

    @Test
    fun homeScreen_ScheduleGenerationActivity_test() {
        with(composeRule) {
            onNodeWithTag(TestTags.ADDING_FINANCE_SCHEDULE)
                .performClick()

            Thread.sleep(1000L)


            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_NOTIFICATION_DATE)
                .performTextInput(notificationDate)

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_NOTIFICATION_DATE)
                .assertTextContains(notificationDate)

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.RADIO_BUTTON_INCOME)
                .performClick()

            Thread.sleep(1000L)


            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_SCHEDULE)
                .performTextInput(schedule)

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_SCHEDULE)
                .assertTextContains(schedule)


            onNodeWithTag(TestTags.BASIC_TEXT_FIELD_COST)
                .performTextInput(cost)

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.BUTTON_ADDING_SCHEDULE)
                .performClick()


            waitUntil(10000) { true }

            onAllNodesWithText("금융 일정이 존재하지 않습니다.")[0].assertDoesNotExist()
            onAllNodesWithText("고정 지출 정보가 존재하지 않습니다.")[0].assertDoesNotExist()

        }
    }

    fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.backButtonPressed() {
        activityRule.scenario.onActivity {
            it.onBackPressedDispatcher.onBackPressed()
        }
    }

    companion object {
        const val notificationDate = "3"
        const val cost = "100000"
        const val schedule = "일정 이름 테스트1"
        const val budget = "60000"

    }
}