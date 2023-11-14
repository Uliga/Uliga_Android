package com.uliga.app.view.accountBook.input

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ExperimentalMaterialApi
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
import com.uliga.app.view.component.toast.TOAST_DURATION_MILLIS
import com.uliga.app.view.component.toast.TOAST_END_POSITION
import com.uliga.app.view.component.toast.TOAST_START_POSITION
import com.uliga.app.view.component.toast.TopDownToast
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ext.CircularProgress
import com.uliga.app.view.component.BasicTextField
import com.uliga.app.view.component.ClassifierRadioButton
import com.uliga.app.view.component.DropDownTextField
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@AndroidEntryPoint
class AccountBookInputActivity : ComponentActivity() {

    private val viewModel: AccountBookInputViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UligaApplicationTheme {

                val context = LocalContext.current
                val state = viewModel.collectAsState().value


                val selectedDate = intent.getStringExtra("selectedDate")

                val scheduleTypeList = listOf("지출", "수입")

                val accountBookSelectedDate by remember {
                    mutableStateOf(
                        selectedDate
                            ?.replace(" ", "-")
                            ?.replace("년", "")
                            ?.replace("월", "")
                            ?.replace("일", "")
                    )
                }

                var accountBookTypeTextState by remember {
                    mutableStateOf("지출")
                }

                var isAccountBookCategoryExpanded by remember {
                    mutableStateOf(false)
                }

                val accountBookCategoryList = listOf(
                    "\uD83C\uDF7D️ 식비",
                    "☕ 카페 · 간식",
                    "\uD83C\uDFE0 생활",
                    "\uD83C\uDF59 편의점,마트,잡화",
                    "\uD83D\uDC55 쇼핑",
                    "기타"
                )

                var accountBookCategoryTextState by remember {
                    mutableStateOf("")
                }

                var accountBookAccountTextState by remember {
                    mutableStateOf("")
                }

                val accountBookPaymentMethodList = listOf("카드", "현금", "이체")

                var isAccountPaymentMethodExpanded by remember {
                    mutableStateOf(false)
                }

                var accountBookPaymentMethodTextState by remember {
                    mutableStateOf("")
                }

                var accountBookCostTextState by remember {
                    mutableStateOf("")
                }

                var accountBookMemoTextState by remember {
                    mutableStateOf("")
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

                viewModel.collectSideEffect { sideEffect ->
                    handleSideEffect(
                        sideEffect = sideEffect,
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
                                    text = "가계부에 일정 추가하기",
                                    color = Grey700,
                                    style = UligaTheme.typography.title2,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            },
                            modifier = Modifier
                                .shadow(5.dp)
                        )
                    },
                    bottomBar = {
                        PositiveButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = "가계부 추가하기",
                            contentPadding = PaddingValues(
                                vertical = 16.dp
                            ),
                            onClick = {
                                viewModel.postAccountBookTransaction(
                                    transactionType = accountBookTypeTextState,
                                    category = accountBookCategoryTextState,
                                    payment = accountBookPaymentMethodTextState,
                                    date = accountBookSelectedDate,
                                    account = accountBookAccountTextState,
                                    value = accountBookCostTextState,
                                    memo = accountBookMemoTextState
                                )
                            }
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

                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                ),
                                text = "${selectedDate ?: ""} 가계부",
                                color = Grey700,
                                style = UligaTheme.typography.title1,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
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

                            Row(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .selectableGroup()
                            ) {
                                scheduleTypeList.forEach { scheduleType ->

                                    ClassifierRadioButton(
                                        selectedItem = accountBookTypeTextState,
                                        scheduleType = scheduleType,
                                        onSelectRequest = {
                                            accountBookTypeTextState = it
                                        }
                                    )
                                }

                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "카테고리",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                DropDownTextField(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxSize(),
                                    expanded = isAccountBookCategoryExpanded,
                                    onExpandedChange = {
                                        isAccountBookCategoryExpanded = it
                                    },
                                    textFieldValue = accountBookCategoryTextState,
                                    onDropDownMenuDismissRequest = {
                                        isAccountBookCategoryExpanded = false
                                    },
                                    onDropDownMenuClick = {
                                        accountBookCategoryTextState = it
                                        isAccountBookCategoryExpanded = false
                                    },
                                    dropDownMenuList = accountBookCategoryList
                                )
                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "결제수단",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                DropDownTextField(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxSize(),
                                    expanded = isAccountPaymentMethodExpanded,
                                    onExpandedChange = {
                                        isAccountPaymentMethodExpanded = it
                                    },
                                    textFieldValue = accountBookPaymentMethodTextState,
                                    onDropDownMenuDismissRequest = {
                                        isAccountPaymentMethodExpanded = false
                                    },
                                    onDropDownMenuClick = {
                                        accountBookPaymentMethodTextState = it
                                        isAccountPaymentMethodExpanded = false
                                    },
                                    dropDownMenuList = accountBookPaymentMethodList
                                )
                            }
                        }


                        item {
                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "거래처",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    BasicTextField(
                                        value = accountBookAccountTextState,
                                        onValueChange = {
                                            accountBookAccountTextState = it
                                        },
                                        keyboardType = KeyboardType.Text
                                    )
                                }
                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
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
                                        value = accountBookCostTextState,
                                        onValueChange = {
                                            accountBookCostTextState = it
                                        },
                                        keyboardType = KeyboardType.Number
                                    )
                                }
                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "메모",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BasicTextField(
                                        value = accountBookMemoTextState,
                                        onValueChange = {
                                            accountBookMemoTextState = it
                                        },
                                        keyboardType = KeyboardType.Text
                                    )
                                }
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
    sideEffect: AccountBookInputSideEffect,
    onFinishRequest: () -> Unit,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is AccountBookInputSideEffect.Finish -> {
            onFinishRequest()
        }

        is AccountBookInputSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

    }
}