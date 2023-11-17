package com.uliga.app.view.budget

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uliga.app.ui.theme.CustomGrey200
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.utils.TestTags
import com.uliga.app.view.component.BasicTextField
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
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
        when (it) {
            HomeSideEffect.FinishBudgetSettingBottomSheet -> {
                onDismissRequest()
            }

            else -> {}
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        containerColor = White
    )
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
                    modifier = Modifier.testTag(TestTags.BUDGET_CURRENT_DATE),
                    text = "${currentDate.monthValue}월 예산 설정",
                    color = Grey700,
                    style = UligaTheme.typography.title3,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(
                    Modifier.weight(1f)
                )
            }

            VerticalSpacer(height = 16.dp)

            Text(
                text = "이번 달도 잘 해내실거라고 생각해요! 저희가 응원합니다\uD83D\uDCAA\uD83C\uDFFB",
                color = Grey500,
                style = UligaTheme.typography.body12,
                maxLines = 1
            )

            VerticalSpacer(height = 32.dp)

            Text(
                text = "금액",
                color = Color.Black,
                style = UligaTheme.typography.body3,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            VerticalSpacer(height = 8.dp)

            BasicTextField(
                value = budgetTextState.value,
                onValueChange = {
                    budgetTextState.value = it
                },
                keyboardType = KeyboardType.Number,
                hint = "이번 달 예산 금액을 입력해주세요.",
                testTag = TestTags.BASIC_TEXT_FIELD_BUDGET
            )

            VerticalSpacer(height = 16.dp)

            Row {
                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(
                            color = CustomGrey200,
                            shape = UligaTheme.shapes.medium
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                ) {
                    Row {
                        Text(
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Start,
                            text = "지난 달 예산",
                            color = Grey500,
                            style = UligaTheme.typography.body12,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        HorizontalSpacer(width = 8.dp)

                        Text(
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.End,
                            text = if (state.beforeMonthAccountBookAsset == null) "0원" else "${state.beforeMonthAccountBookAsset.budget.value}원",
                            color = Primary,
                            style = UligaTheme.typography.body12,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Row {
                        Text(
                            modifier = Modifier.width(100.dp),
                            textAlign = TextAlign.Start,
                            text = "결과",
                            color = Grey500,
                            style = UligaTheme.typography.body12,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        HorizontalSpacer(width = 8.dp)

                        val beforeMonthBudget =
                            state.beforeMonthAccountBookAsset?.budget?.value ?: 0L
                        val beforeMonthRecord =
                            state.beforeMonthAccountBookAsset?.record?.value ?: 0L

                        val beforeMonthResult = beforeMonthBudget - beforeMonthRecord

                        Row(
                            modifier = Modifier.width(100.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = "${kotlin.math.abs(beforeMonthResult)}",
                                color = Primary,
                                style = UligaTheme.typography.body12,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                textAlign = TextAlign.End,
                                text = if (beforeMonthResult >= 0) "남음" else "초과",
                                color = Secondary,
                                style = UligaTheme.typography.body12,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }

                    }
                }
            }

            VerticalSpacer(height = 16.dp)

            PositiveButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .testTag(TestTags.BUTTON_BUDGET_SETTING),
                text = "예산 등록",
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                onClick = {
                    val currentAccountBookBudget = state.currentMonthAccountBookAsset?.budget?.value ?: return@PositiveButton

                    if (currentAccountBookBudget == 0L) {
                        viewModel.postAccountBookBudget(
                            currentDate.year.toLong(),
                            currentDate.monthValue.toLong(),
                            budgetTextState.value
                        )
                    } else {
                        viewModel.patchAccountBookBudget(
                            currentDate.year.toLong(),
                            currentDate.monthValue.toLong(),
                            budgetTextState.value
                        )
                    }
                }
            )
        }
    }
}


