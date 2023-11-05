package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Danger100
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.Success200
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.home.HomeUiState
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun FinanceScheduleItem(
    state: HomeUiState,
    idx: Int,
    currentDay: Int,
    onFinanceScheduleUpdateRequest: (FinanceSchedule) -> Unit,
    onFinanceScheduleDeleteRequest: (FinanceSchedule) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val financeSchedule = FinanceSchedule(
                    id = state.financeSchedules?.schedules?.get(idx)?.id ?: 0L,
                    notificationDay = state.financeSchedules?.schedules?.get(idx)?.notificationDay
                        ?: 0L,
                    name = state.financeSchedules?.schedules?.get(idx)?.name ?: "",
                    isIncome = state.financeSchedules?.schedules?.get(idx)?.isIncome
                        ?: false,
                    value = state.financeSchedules?.schedules?.get(idx)?.value ?: 0L,
                    creatorId = 0L,
                    creator = "",
                    accountBookName = ""
                )

                onFinanceScheduleUpdateRequest(financeSchedule)

            }
    ) {
        Box(
            modifier = Modifier
                .height(48.dp)
                .width(64.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    notificationDayColor(
                        currentDay,
                        state.financeSchedules?.schedules?.get(idx)?.notificationDay
                            ?: 0
                    )
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                color = White,
                text = "${state.financeSchedules?.schedules?.get(idx)?.notificationDay ?: 0}일",
                fontSize = 16.sp
            )
        }

        HorizontalSpacer(width = 16.dp)

        Column {
            Text(
                text = state.financeSchedules?.schedules?.get(idx)?.name ?: "",
                color = Grey700,
                fontSize = 18.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "${state.financeSchedules?.schedules?.get(idx)?.value ?: ""}원 / ${
                    if (state.financeSchedules?.schedules?.get(
                            idx
                        )?.isIncome != false
                    ) "수입" else "지출"
                }",
                color = Grey400,
                fontSize = 14.sp,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    val financeSchedule = FinanceSchedule(
                        id = state.financeSchedules?.schedules?.get(idx)?.id ?: 0L,
                        notificationDay = state.financeSchedules?.schedules?.get(idx)?.notificationDay
                            ?: 0L,
                        name = state.financeSchedules?.schedules?.get(idx)?.name ?: "",
                        isIncome = state.financeSchedules?.schedules?.get(idx)?.isIncome
                            ?: false,
                        value = state.financeSchedules?.schedules?.get(idx)?.value
                            ?: 0L,
                        creatorId = 0L,
                        creator = "",
                        accountBookName = ""
                    )

                    onFinanceScheduleDeleteRequest(financeSchedule)
//                            viewModel.deleteFinanceScheduleDetail(
//                                state.financeSchedules?.schedules?.get(
//                                    idx
//                                )?.id ?: 0L
//                            )
                },
            painter = painterResource(
                id = R.drawable.ic_cancel
            ),
            contentDescription = null
        )
    }
}

fun notificationDayColor(currentDay: Int, notificationDay: Long): Color {

    return if (currentDay - notificationDay < 3) {
        Danger100
    } else if (currentDay - notificationDay < 7) {
        Secondary
    } else {
        Success200
    }

}