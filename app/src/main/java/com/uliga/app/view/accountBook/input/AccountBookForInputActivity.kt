package com.uliga.app.view.accountBook.input

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.MyApplicationTheme
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.home.HomeSideEffect
import com.uliga.app.view.schedule.RadioButtonWithNoCheckBox
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@AndroidEntryPoint
class AccountBookForInputActivity : ComponentActivity() {

    private val viewModel: AccountBookForInputViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val selectedDate = getIntent().getStringExtra("selectedDate")

                var accountBookSelectedDate by remember {
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

                var accountBookCategoryList = listOf(
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

                var accountBookPaymentMethodList = listOf("카드", "현금", "이체")

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

                viewModel.collectSideEffect { sideEffect ->
                    when (sideEffect) {
                        is AccountBookForInputSideEffect.Finish -> {
                            setResult(RESULT_OK)
                            finish()
                        }

                        is AccountBookForInputSideEffect.ToastMessage -> {

                        }
                    }
                }

                val state = viewModel.collectAsState().value

                val pullRefreshState = rememberPullRefreshState(
                    refreshing = state.isLoading,
                    onRefresh = {

                    }
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .pullRefresh(pullRefreshState),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 16.dp
                            )
                            .wrapContentSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        item {
                            Text(
                                modifier = Modifier.padding(
                                    end = 16.dp,
                                    bottom = 4.dp
                                ),
                                text = "${selectedDate ?: ""} 가계부 작성",
                                color = Color.Black,
                                fontFamily = pretendard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }

                        item {
                            Text(
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "분류",
                            )

                            val scheduleTypeList = listOf("지출", "수입")

                            Row(
                                modifier = Modifier
                                    .selectableGroup()
                            ) {
                                scheduleTypeList.forEach { scheduleType ->

                                    RadioButtonWithNoCheckBox(
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
                                        .fillMaxWidth(),
                                    text = "카테고리",
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(8.dp)
                                )


                                ExposedDropdownMenuBox(
                                    modifier = Modifier.fillMaxSize(),
                                    expanded = isAccountBookCategoryExpanded,
                                    onExpandedChange = {
                                        isAccountBookCategoryExpanded = it
                                    }
                                ) {
                                    TextField(
                                        value = accountBookCategoryTextState,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isAccountBookCategoryExpanded)
                                        },
                                        textStyle = TextStyle(
                                            color = Color.Black, fontSize = 16.sp
                                        ),
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                                            unfocusedContainerColor = White,
                                            focusedContainerColor = White,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        singleLine = true,
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Grey400,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .fillMaxWidth()
                                            .menuAnchor()
                                    )

                                    DropdownMenu(
                                        expanded = isAccountBookCategoryExpanded,
                                        onDismissRequest = {
                                            isAccountBookCategoryExpanded = false
                                        },
                                        modifier = Modifier
                                            .background(White)
                                            .exposedDropdownSize(),
                                    ) {

                                        accountBookCategoryList.forEach { accountBookCategory ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    color = Color.Black,
                                                    fontFamily = pretendard,
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 16.sp,
                                                    text = accountBookCategory
                                                )
                                            }, onClick = {
                                                accountBookCategoryTextState = accountBookCategory
                                                isAccountBookCategoryExpanded = false
                                            })
                                        }

                                    }
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
                                        .fillMaxWidth(),
                                    text = "결제수단",
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(8.dp)
                                )


                                ExposedDropdownMenuBox(
                                    modifier = Modifier.fillMaxSize(),
                                    expanded = isAccountPaymentMethodExpanded,
                                    onExpandedChange = {
                                        isAccountPaymentMethodExpanded = it
                                    }
                                ) {
                                    TextField(
                                        value = accountBookPaymentMethodTextState,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isAccountPaymentMethodExpanded)
                                        },
                                        textStyle = TextStyle(
                                            color = Color.Black, fontSize = 16.sp
                                        ),
                                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                                            unfocusedContainerColor = White,
                                            focusedContainerColor = White,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        singleLine = true,
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Grey400,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .fillMaxWidth()
                                            .menuAnchor()
                                    )

                                    DropdownMenu(
                                        expanded = isAccountPaymentMethodExpanded,
                                        onDismissRequest = {
                                            isAccountPaymentMethodExpanded = false
                                        },
                                        modifier = Modifier
                                            .background(White)
                                            .exposedDropdownSize(),
                                    ) {

                                        accountBookPaymentMethodList.forEach { accountBookPaymentMethod ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    color = Color.Black,
                                                    fontFamily = pretendard,
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 16.sp,
                                                    text = accountBookPaymentMethod
                                                )
                                            }, onClick = {
                                                accountBookPaymentMethodTextState =
                                                    accountBookPaymentMethod
                                                isAccountPaymentMethodExpanded = false
                                            })
                                        }

                                    }
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
                                        .fillMaxWidth(),
                                    text = "거래처",
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(8.dp)
                                )


                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    androidx.compose.material.TextField(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Grey400,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .fillMaxWidth(),
                                        value = accountBookAccountTextState,
                                        onValueChange = {
                                            accountBookAccountTextState = it
                                        },
                                        textStyle = TextStyle(
                                            color = Color.Black, fontSize = 16.sp
                                        ),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        singleLine = true,
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
                                        .fillMaxWidth(),
                                    text = "금액",
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(8.dp)
                                )


                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    androidx.compose.material.TextField(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Grey400,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .fillMaxWidth(),
                                        value = accountBookCostTextState,
                                        onValueChange = {
                                            accountBookCostTextState = it
                                        },
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Number
                                        ),
                                        textStyle = TextStyle(
                                            color = Color.Black, fontSize = 16.sp
                                        ),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        singleLine = true,
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
                                        .fillMaxWidth(),
                                    text = "메모",
                                )

                                Spacer(
                                    modifier = Modifier
                                        .height(8.dp)
                                )


                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    androidx.compose.material.TextField(
                                        modifier = Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Grey400,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .fillMaxWidth(),
                                        value = accountBookMemoTextState,
                                        onValueChange = {
                                            accountBookMemoTextState = it
                                        },
                                        textStyle = TextStyle(
                                            color = Color.Black, fontSize = 16.sp
                                        ),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                        ),
                                        singleLine = true,
                                    )

                                }
                            }
                        }

                        item {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentPadding = PaddingValues(
                                    vertical = 16.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Primary,
                                ),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    if (accountBookSelectedDate == null) return@Button

                                    viewModel.postAccountBookTransaction(
                                        transactionType = accountBookTypeTextState,
                                        category = accountBookCategoryTextState,
                                        payment = accountBookPaymentMethodTextState,
                                        date = accountBookSelectedDate!!,
                                        account = accountBookAccountTextState,
                                        value = accountBookCostTextState.toLong(),
                                        memo = accountBookMemoTextState
                                    )

                                }) {
                                Text(
                                    color = Color.White,
                                    fontFamily = pretendard,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 16.sp,
                                    text = "가계부 추가하기"
                                )
                            }
                        }

                    }

//                AccountBookForInputBottomSheetCompose()

                    PullRefreshIndicator(
                        refreshing = state.isLoading,
                        state = pullRefreshState
                    )
                }

                if(state.isLoading) {
                    CircularProgress()
                }
            }

        }
    }
}