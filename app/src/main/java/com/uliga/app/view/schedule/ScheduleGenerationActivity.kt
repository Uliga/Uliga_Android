package com.uliga.app.view.schedule

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.ext.CircularProgress
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.utils.TestTags.BASIC_TEXT_FIELD_COST
import com.uliga.app.utils.TestTags.BASIC_TEXT_FIELD_NOTIFICATION_DATE
import com.uliga.app.utils.TestTags.BASIC_TEXT_FIELD_SCHEDULE
import com.uliga.app.utils.TestTags.BUTTON_ADDING_SCHEDULE
import com.uliga.app.utils.TestTags.RADIO_BUTTON_CHARGE
import com.uliga.app.utils.TestTags.RADIO_BUTTON_INCOME
import com.uliga.app.view.component.BasicTextField
import com.uliga.app.view.component.ClassifierRadioButton
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.component.toast.TOAST_DURATION_MILLIS
import com.uliga.app.view.component.toast.TOAST_END_POSITION
import com.uliga.app.view.component.toast.TOAST_START_POSITION
import com.uliga.app.view.component.toast.TopDownToast
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class ScheduleGenerationActivity : ComponentActivity() {

    private val viewModel: ScheduleGenerationViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UligaApplicationTheme {

                val context = LocalContext.current
                val state = viewModel.collectAsState().value

                val notificationDay = intent.getLongExtra("notificationDay", 0L)
                val isIncome = intent.getBooleanExtra("isIncome", false)
                val value = intent.getLongExtra("value", 0L)
                val scheduleId = intent.getLongExtra("scheduleId", 0L)

                var notificationDateTextState by
                remember { mutableStateOf(if (notificationDay == 0L) "" else notificationDay.toString()) }

                var scheduleTextState by
                remember { mutableStateOf(intent.getStringExtra("name") ?: "") }

                var costTextState by
                remember { mutableStateOf(if (value == 0L) "" else value.toString()) }

                var selectedItemState by remember {
                    mutableStateOf(if (isIncome) "수입" else "지출")
                }

                /**
                 * Toast Message
                 */

                var isToastAnimating by remember {
                    mutableStateOf(false)
                }

                var toastMessage by remember {
                    mutableStateOf("")
                }

                val toastYOffset by animateFloatAsState(
                    targetValue = if (isToastAnimating) TOAST_END_POSITION else TOAST_START_POSITION,
                    animationSpec = tween(durationMillis = TOAST_DURATION_MILLIS),
                    finishedListener = { endValue ->
                        if (endValue == TOAST_END_POSITION) {
                            isToastAnimating = false
                        }
                    },
                    label = "",
                )

                /**
                 * SideEffect
                 */

                viewModel.collectSideEffect {
                    handleSideEffect(
                        sideEffect = it,
                        onFinishRequest = {
                            setResult(RESULT_OK)
                            finish()
                        },
                        onShowToast = {
                            isToastAnimating = true
                            toastMessage = it
                        }
                    )
                }

                Scaffold(
                    containerColor = White,
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = White
                            ),
                            title = {
                                Text(
                                    text = "금융 일정 추가하기",
                                    color = Grey700,
                                    style = UligaTheme.typography.title2,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            },
                            modifier = Modifier.shadow(5.dp)
                        )
                    },
                    bottomBar = {
                        PositiveButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = "금융 일정 추가하기",
                            contentPadding = PaddingValues(
                                vertical = 16.dp
                            ),
                            onClick = {
                                if (scheduleId != 0L) {
                                    viewModel.patchFinanceSchedule(
                                        scheduleId = scheduleId,
                                        name = scheduleTextState,
                                        isIncome = selectedItemState == "수입",
                                        notificationDate = notificationDateTextState,
                                        value = costTextState
                                    )
                                } else {
                                    viewModel.postFinanceScheduleToAccountBook(
                                        name = scheduleTextState,
                                        isIncome = selectedItemState == "수입",
                                        notificationDate = notificationDateTextState,
                                        value = costTextState
                                    )
                                }
                            },
                            testTag = BUTTON_ADDING_SCHEDULE
                        )
                    }
                ) { contentPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(contentPadding)
                            .wrapContentSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {


                        item {

                            VerticalSpacer(height = 32.dp)

                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "날짜",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .wrapContentSize(),
                                        text = "매달",
                                        color = Grey500,
                                        style = UligaTheme.typography.body12
                                    )

                                    HorizontalSpacer(width = 8.dp)

                                    Row(
                                        modifier = Modifier.width(100.dp)
                                    ) {
                                        BasicTextField(
                                            value = notificationDateTextState,
                                            onValueChange = {
                                                notificationDateTextState = it
                                            },
                                            keyboardType = KeyboardType.Number,
                                            testTag = BASIC_TEXT_FIELD_NOTIFICATION_DATE
                                        )
                                    }

                                    HorizontalSpacer(width = 8.dp)

                                    Text(
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .wrapContentSize(),
                                        text = "일",
                                        color = Grey500,
                                        style = UligaTheme.typography.body12
                                    )
                                }
                            }
                        }

                        item {
                            Text(
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = "분류",
                                color = Grey600,
                                style = UligaTheme.typography.body3
                            )

                            VerticalSpacer(height = 8.dp)

                            val scheduleTypeList = listOf("지출", "수입")

                            Row(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .selectableGroup()
                            ) {
                                scheduleTypeList.forEach { scheduleType ->

                                    ClassifierRadioButton(
                                        selectedItem = selectedItemState,
                                        scheduleType = scheduleType,
                                        onSelectRequest = {
                                            selectedItemState = it
                                        },
                                        testTag = if (scheduleType == "지출") RADIO_BUTTON_CHARGE else RADIO_BUTTON_INCOME
                                    )
                                }

                            }
                        }

                        item {

                            Text(
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = "일정 이름",
                                color = Grey600,
                                style = UligaTheme.typography.body3
                            )

                            VerticalSpacer(height = 8.dp)

                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BasicTextField(
                                    value = scheduleTextState,
                                    onValueChange = {
                                        scheduleTextState = it
                                    },
                                    keyboardType = KeyboardType.Text,
                                    testTag = BASIC_TEXT_FIELD_SCHEDULE
                                )
                            }
                        }

                        item {

                            Text(
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = "금액",
                                color = Grey600,
                                style = UligaTheme.typography.body3
                            )

                            VerticalSpacer(height = 8.dp)

                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                BasicTextField(
                                    value = costTextState,
                                    onValueChange = {
                                        costTextState = it
                                    },
                                    keyboardType = KeyboardType.Number,
                                    testTag = BASIC_TEXT_FIELD_COST
                                )
                            }

                        }
                    }
                }


                if (state.isLoading) {
                    CircularProgress()
                }

                TopDownToast(toastYOffset = toastYOffset, toastMessage = toastMessage)

            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: ScheduleGenerationSideEffect,
    onFinishRequest: () -> Unit,
    onShowToast: (String) -> Unit,
) {
    when (sideEffect) {
        is ScheduleGenerationSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

        is ScheduleGenerationSideEffect.Finish -> {
            onFinishRequest()
        }
    }
}