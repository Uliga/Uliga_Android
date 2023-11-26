package com.uliga.app

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.uliga.app.utils.TestTags
import com.uliga.app.utils.TestTags.ADDING_ACCOUNT_BOOK
import com.uliga.app.utils.TestTags.FINANCE_CURRENT_DATE
import com.uliga.app.utils.TestTags.TRANSACTION_CURRENT_DATE
import com.uliga.app.view.finance.FinanceScreen
import com.uliga.app.view.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@HiltAndroidTest
class FinanceScreenTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {

            FinanceScreen()
        }
    }

    @Test
    fun financeScreen_test() {

        with(composeRule) {

            onNodeWithTag(TestTags.FINANCE_CURRENT_MONTH)
                .assertTextEquals("${month}월")

            onNodeWithTag(FINANCE_CURRENT_DATE)
                .assertTextEquals("${year}년 ${month}월 ${day}일")

        }
    }

    @Test
    fun financeScreen_AccountBookInputActivity_test() {
        with(composeRule) {
            onNodeWithTag(ADDING_ACCOUNT_BOOK)
                .performClick()

            Thread.sleep(1000L)

            onNodeWithTag(TRANSACTION_CURRENT_DATE)
                .assertTextEquals("${year}년 ${month}월 ${day}일 가계부")

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.RADIO_BUTTON_INCOME)
                .performClick()

            Thread.sleep(1000L)

            onNodeWithTag(TestTags.DROP_DOWN_TEXT_FIELD_CATEGORY)
                .assertHasClickAction()

            onNodeWithTag(TestTags.DROP_DOWN_TEXT_FIELD_PAYMENT_METHOD)
                .assertHasClickAction()
        }
    }

    companion object {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue
        val day = currentDate.dayOfMonth

        const val categoryItem = "☕ 카페 · 간식"
    }
}

