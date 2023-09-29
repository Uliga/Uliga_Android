package com.uliga.app.view.accountbook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.schedule.RadioButtonWithNoCheckBox
import com.uliga.app.view.schedule.ScheduleBottomSheetCompose


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AccountBookForInputBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest
        })
    {

        ScheduleBottomSheetCompose()
    }


}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AccountBookForInputBottomSheetCompose() {

    var accountBookTypeTextState by remember {
        mutableStateOf("지출")
    }

    var accountBookCategoryTextState  by remember {
        mutableStateOf("")
    }

    var accountBookAccountTextState  by remember {
        mutableStateOf("")
    }


    var accountBookPaymentMethodTextState  by remember {
        mutableStateOf("")
    }

    var accountBookCostTextState  by remember {
        mutableStateOf("")
    }

    var accountBookMemoTextState  by remember {
        mutableStateOf("")
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
                text = "2023년 9월 23일 가계부 작성",
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
                        scheduleType = scheduleType
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
                        value = accountBookCategoryTextState,
                        onValueChange = {
                            accountBookCategoryTextState = it
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
                    text = "결제수단",
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
                        value = accountBookPaymentMethodTextState,
                        onValueChange = {
                            accountBookPaymentMethodTextState = it
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
                    text = "거래처",
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

                    TextField(
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

                    TextField(
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
}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Preview() {
    AccountBookForInputBottomSheetCompose()
}