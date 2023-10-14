package com.uliga.app.view.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.accountBook.generation.AccountBookGenerationSideEffect
import com.uliga.app.view.home.HomeSideEffect
import com.uliga.app.view.home.HomeViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleBottomSheet(
    sheetState: SheetState,
    viewModel: HomeViewModel,
    onDismissRequest: () -> Unit
) {

    val state = viewModel.collectAsState().value

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest()
        })
    {

        val notificationDateTextState = remember { mutableStateOf("") }
        val scheduleTextState = remember { mutableStateOf("") }
        val costTextState = remember { mutableStateOf("") }
        var selectedItem by remember {
            mutableStateOf("지출")
        }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HomeSideEffect.ToastMessage -> {
//                    toastRequest(sideEffect.toastMessage)
                }

                is HomeSideEffect.FinishScheduleBottomSheet -> {
                    onDismissRequest()
                }

                else -> {

                }
            }
        }

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
                    text = "나의 금융 일정",
                    color = Color.Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
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
                        text = "날짜",
                    )

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize(),
                            text = "매달",
                        )

                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        TextField(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Grey400,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .width(100.dp),
                            value = notificationDateTextState.value,
                            onValueChange = {
                                notificationDateTextState.value = it
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

                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        Text(
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize(),
                            text = "일",
                        )
                    }
                }
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
                            selectedItem = selectedItem,
                            scheduleType = scheduleType,
                            onSelectRequest = {
                                selectedItem = it
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
                        text = "일정 이름",
                    )

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextField(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Grey400,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth(),
                            value = scheduleTextState.value,
                            onValueChange = {
                                scheduleTextState.value = it
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

                        TextField(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Grey400,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .fillMaxWidth(),
                            value = costTextState.value,
                            onValueChange = {
                                costTextState.value = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontSize = 16.sp
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
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
                        viewModel.postFinanceScheduleToAccountBook(
                            name = scheduleTextState.value,
                            isIncome = selectedItem == "수입",
                            notificationDate = notificationDateTextState.value.toLong(),
                            value = costTextState.value.toLong()
                        )

                    }) {
                    Text(
                        color = Color.White,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        text = "금융 일정 추가하기"
                    )
                }
            }
        }

    }
}

//@RequiresApi(Build.VERSION_CODES.Q)
//@Composable
//fun ScheduleBottomSheetCompose() {
//
//    val emailAddressTextState = remember { mutableStateOf("") }
//    val scheduleTextState = remember { mutableStateOf("") }
//    val costTextState = remember { mutableStateOf("") }
//    var selectedItem by remember {
//        mutableStateOf("지출")
//    }
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(
//                horizontal = 16.dp,
//                vertical = 16.dp
//            )
//            .wrapContentSize()
//            .background(Color.White),
//        verticalArrangement = Arrangement.spacedBy(20.dp)
//    ) {
//        item {
//            Text(
//                modifier = Modifier.padding(
//                    end = 16.dp,
//                    bottom = 4.dp
//                ),
//                text = "나의 금융 일정",
//                color = Color.Black,
//                fontFamily = pretendard,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 1
//            )
//        }
//
//        item {
//            Column(
//                modifier = Modifier
//                    .wrapContentWidth()
//            ) {
//                Text(
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    text = "날짜",
//                )
//
//                Spacer(
//                    modifier = Modifier
//                        .height(8.dp)
//                )
//
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    Text(
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier
//                            .wrapContentSize(),
//                        text = "매달",
//                    )
//
//                    Spacer(
//                        modifier = Modifier
//                            .width(8.dp)
//                    )
//
//                    TextField(
//                        modifier = Modifier
//                            .border(
//                                width = 1.dp,
//                                color = Grey400,
//                                shape = RoundedCornerShape(10.dp)
//                            )
//                            .width(100.dp),
//                        value = emailAddressTextState.value,
//                        onValueChange = {
//                            emailAddressTextState.value = it
//                        },
//                        textStyle = TextStyle(
//                            color = Color.Black, fontSize = 16.sp
//                        ),
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                        ),
//                        singleLine = true,
//                    )
//
//                    Spacer(
//                        modifier = Modifier
//                            .width(8.dp)
//                    )
//
//                    Text(
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier
//                            .wrapContentSize(),
//                        text = "일",
//                    )
//                }
//            }
//        }
//
//        item {
//            Text(
//                textAlign = TextAlign.Start,
//                modifier = Modifier
//                    .fillMaxWidth(),
//                text = "분류",
//            )
//
//            val scheduleTypeList = listOf("지출", "수입")
//
//            Row(
//                modifier = Modifier
//                    .selectableGroup()
//            ) {
//                scheduleTypeList.forEach { scheduleType ->
//
//                    RadioButtonWithNoCheckBox(
//                        selectedItem = selectedItem,
//                        scheduleType = scheduleType
//                    )
//                }
//
//            }
//        }
//
//        item {
//            Column(
//                modifier = Modifier
//                    .wrapContentWidth()
//            ) {
//                Text(
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    text = "일정 이름",
//                )
//
//                Spacer(
//                    modifier = Modifier
//                        .height(8.dp)
//                )
//
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    TextField(
//                        modifier = Modifier
//                            .border(
//                                width = 1.dp,
//                                color = Grey400,
//                                shape = RoundedCornerShape(10.dp)
//                            )
//                            .fillMaxWidth(),
//                        value = scheduleTextState.value,
//                        onValueChange = {
//                            scheduleTextState.value = it
//                        },
//                        textStyle = TextStyle(
//                            color = Color.Black, fontSize = 16.sp
//                        ),
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                        ),
//                        singleLine = true,
//                    )
//
//                }
//            }
//        }
//
//        item {
//            Column(
//                modifier = Modifier
//                    .wrapContentWidth()
//            ) {
//                Text(
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    text = "금액",
//                )
//
//                Spacer(
//                    modifier = Modifier
//                        .height(8.dp)
//                )
//
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    TextField(
//                        modifier = Modifier
//                            .border(
//                                width = 1.dp,
//                                color = Grey400,
//                                shape = RoundedCornerShape(10.dp)
//                            )
//                            .fillMaxWidth(),
//                        value = costTextState.value,
//                        onValueChange = {
//                            costTextState.value = it
//                        },
//                        textStyle = TextStyle(
//                            color = Color.Black, fontSize = 16.sp
//                        ),
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                        ),
//                        singleLine = true,
//                    )
//
//                }
//            }
//        }
//
//        item {
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                contentPadding = PaddingValues(
//                    vertical = 16.dp
//                ),
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Primary,
//                ),
//                shape = RoundedCornerShape(10.dp),
//                onClick = {
//
//
//                }) {
//                Text(
//                    color = Color.White,
//                    fontFamily = pretendard,
//                    fontWeight = FontWeight.Light,
//                    fontSize = 16.sp,
//                    text = "금융 일정 추가하기"
//                )
//            }
//        }
//    }
//}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RadioButtonWithNoCheckBox(
    selectedItem: String,
    scheduleType: String,
    onSelectRequest: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .border(
                width = 1.dp,
                color = if (selectedItem == scheduleType) {
                    Primary
                } else {
                    Grey400
                },
                shape = RoundedCornerShape(10)
            )
            .selectable(
                selected = (selectedItem == scheduleType),
                onClick = {
                    onSelectRequest(scheduleType)
                },
                role = Role.RadioButton
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            color = if (selectedItem == scheduleType) {
                Primary
            } else {
                Grey400
            },
            fontFamily = pretendard,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            text = scheduleType
        )
    }
}

