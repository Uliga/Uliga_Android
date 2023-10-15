package com.uliga.app.view.budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.home.HomeSideEffect
import com.uliga.app.view.home.HomeViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BudgetSettingBottomSheet(
    sheetState: SheetState,
    viewModel: HomeViewModel = hiltViewModel(),
    currentDate: LocalDate,
    onDismissRequest: () -> Unit
) {

    val budgetTextState = remember { mutableStateOf("") }

    val state = viewModel.collectAsState().value

    viewModel.collectSideEffect {
        when(it) {
            HomeSideEffect.FinishBudgetSettingBottomSheet -> {
                onDismissRequest()
            }

            else -> {

            }
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest()
        })
    {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.White)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp,
                )
        ) {

            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = "${currentDate.monthValue}월 예산 설정",
                    color = Color.Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(
                    Modifier.weight(1f)
                )

                Image(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable {
                            onDismissRequest()
                        },
                    painter = painterResource(
                        id = R.drawable.ic_cancel
                    ),
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "이번 달도 잘 해내실거라고 생각해요! 저희가 응원합니다\uD83D\uDCAA\uD83C\uDFFB",
                color = Grey500,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "금액",
                color = Color.Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {


                TextField(
                    modifier = Modifier
                        .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    value = budgetTextState.value,
                    onValueChange = {
                        budgetTextState.value = it
                    },
                    label = {
                        Text(
                            text = if (state.currentMonthAccountBookAsset?.budget?.value == null || state.currentMonthAccountBookAsset.budget.value == 0L) "이번 달 예산 금액을 입력해주세요." else "${state.currentMonthAccountBookAsset.budget.value}원",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                )

//            Text(
//                text = "원",
//                color = Color.Black,
//                fontFamily = pretendard,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 14.sp,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 1
//            )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE9EEFF),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(Color(0xFFE9EEFF))
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                ) {
                    Row {
                        Text(
                            text = "지난 달 예산",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = if (state.beforeMonthAccountBookAsset == null) "0원" else "${state.beforeMonthAccountBookAsset.budget.value}원",
                            color = Primary,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Row {
                        Text(
                            text = "결과",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        val beforeMonthBudget =
                            state.beforeMonthAccountBookAsset?.budget?.value ?: 0L
                        val beforeMonthRecord =
                            state.beforeMonthAccountBookAsset?.record?.value ?: 0L

                        val beforeMonthResult = beforeMonthBudget - beforeMonthRecord

                        Text(
                            text = "${kotlin.math.abs(beforeMonthResult)}",
                            color = Primary,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = if (beforeMonthResult >= 0) "남음" else "초과",
                            color = Secondary,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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

                    val currentAccountBookBudget = state.currentMonthAccountBookAsset?.budget ?: 0L

                    if(currentAccountBookBudget == 0L) {
                        viewModel.postAccountBookBudget(
                            currentDate.year.toLong(),
                            currentDate.monthValue.toLong(),
                            budgetTextState.value.toLong()
                        )
                    } else {
                        viewModel.patchAccountBookBudget(
                            currentDate.year.toLong(),
                            currentDate.monthValue.toLong(),
                            budgetTextState.value.toLong()
                        )
                    }

                }) {
                Text(
                    color = Color.White,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    text = "예산 등록"
                )
            }
        }
//        BudgetSettingBottomSheetCompose()
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BudgetSettingBottomSheetCompose() {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White)
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            )
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                text = "3월 예산 설정",
                color = Color.Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(
                Modifier.weight(1f)
            )

            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(
                    id = R.drawable.ic_cancel
                ),
                contentDescription = "uliga logo"
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "이번 달도 잘 해내실거라고 생각해요! 저희가 응원합니다\uD83D\uDCAA\uD83C\uDFFB",
            color = Grey500,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "금액",
            color = Color.Black,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val budgetTextState = remember { mutableStateOf("") }

            TextField(
                modifier = Modifier
                    .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth(),
                value = budgetTextState.value,
                onValueChange = {
                    budgetTextState.value = it
                },
                label = {
                    Text(
                        text = "이번 달 예산 금액을 입력해주세요.",
                        color = Grey500,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black, fontSize = 20.sp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
            )

//            Text(
//                text = "원",
//                color = Color.Black,
//                fontFamily = pretendard,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 14.sp,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 1
//            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE9EEFF),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Color(0xFFE9EEFF))
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
            ) {
                Row {
                    Text(
                        text = "지난 달 예산",
                        color = Grey500,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "800,000원",
                        color = Primary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Row {
                    Text(
                        text = "결과",
                        color = Grey500,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "128,000원",
                        color = Primary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "초과",
                        color = Secondary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                text = "예산 등록"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Preview() {
    BudgetSettingBottomSheetCompose()
}


