package com.uliga.app.view.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.uliga.app.R
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.Danger100
import com.uliga.app.ui.theme.Grey300
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.budget.BudgetSettingBottomSheet
import com.uliga.app.view.home.invitation.InvitationBottomSheet
import com.uliga.app.view.schedule.ScheduleAlertBottomSheet
import com.uliga.app.view.main.MainUiEvent
import com.uliga.app.view.main.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(
    navController: NavController,
    data: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
) {

    val context = LocalContext.current

    val budgetSettingSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isBudgetSettingSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isBudgetSettingSheetOpen) {
        BudgetSettingBottomSheet(
            sheetState = budgetSettingSheetState,
            onDismissRequest = {
                isBudgetSettingSheetOpen = false
            }
        )
    }

    val invitationListSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isInvitationListSheetSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isInvitationListSheetSheetOpen) {
        InvitationBottomSheet(
            sheetState = invitationListSheetState,
            onDismissRequest = {
                isInvitationListSheetSheetOpen = false
            }
        )
    }

    val scheduleAlertSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isScheduleAlertSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isScheduleAlertSheetOpen) {
        ScheduleAlertBottomSheet(
            sheetState = scheduleAlertSheetState,
            onDismissRequest = {
                isScheduleAlertSheetOpen = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        item {

            Row {
                Image(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(
                        id = R.drawable.uliga_logo
                    ),
                    contentDescription = "uliga logo"
                )

                Text(
                    modifier = Modifier.padding(
                        end = 12.dp
                    ),
                    text = "안세훈님의 가계부",
                    color = Primary,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(
                    Modifier.weight(1f)
                )

                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            isInvitationListSheetSheetOpen = true
                        },
                    painter = painterResource(
                        id = R.drawable.uliga_logo
                    ),
                    contentDescription = "tmp invitiation"
                )

                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            isScheduleAlertSheetOpen = true
                        },
                    painter = painterResource(
                        id = R.drawable.uliga_logo
                    ),
                    contentDescription = "tmp schedule"
                )
            }
        }

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp
                    ),
                    text = "이번 달 예산",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = Medium,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.weight(1f))


                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            isBudgetSettingSheetOpen = true
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(
                            id = R.drawable.ic_adding_budget
                        ),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "예산 설정하러 가기",
                        color = Secondary,
                        fontFamily = pretendard,
                        fontWeight = Medium,
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                ),
                text = "195,855원 남음",
                color = Grey600,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Image(
                modifier = Modifier
                    .height(56.dp)
                    .padding(
                        horizontal = 8.dp
                    ),
                painter = painterResource(
                    id = R.drawable.tmp_progress_bar
                ),
                contentDescription = "uliga logo"
            )

            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            Divider(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                ),
                color = Grey300,
                thickness = 1.dp
            )

            Spacer(
                modifier = Modifier.height(
                    12.dp
                )
            )

            Row(
                modifier = Modifier.padding(
                    start = 16.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(
                        id = R.drawable.ic_gray_dot
                    ),
                    contentDescription = "gary dot"
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "3월 설정 예산",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.width(24.dp)
                )
                Text(
                    text = "800,000원",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )

            }

            Spacer(
                modifier = Modifier.height(
                    12.dp
                )
            )


            Row(
                modifier = Modifier.padding(
                    start = 16.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(
                        id = R.drawable.ic_gray_dot
                    ),
                    contentDescription = "gary dot"
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "남은 1일 예산",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.width(24.dp)
                )
                Text(
                    text = "11,520원",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

//            Column(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .border(
//                        width = 1.dp,
//                        color = Grey300,
//                        shape = RoundedCornerShape(12.dp)
//                    )
//            ) {
//
//            }
        }

        item {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp
                ),
                text = "이번 달 총 지출",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = Medium,
                fontSize = 20.sp
            )

            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                ),
                text = "10,000원",
                color = Grey600,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            val pointsData: List<Point> =
                listOf(
                    Point(0f, 40f),
                    Point(1f, 90f),
                    Point(2f, 0f),
                    Point(3f, 60f),
                    Point(4f, 10f),
                    Point(5f, 0f),
                    Point(6f, 0f),
                    Point(7f, 0f),
                    Point(8f, 0f),
                    Point(9f, 0f),
                    Point(10f, 0f),
                    Point(11f, 0f),
                    Point(12f, 0f),
                    Point(13f, 0f),
                    Point(14f, 0f),
                    Point(15f, 0f),
                    Point(16f, 0f),
                    Point(17f, 0f),
                    Point(18f, 0f),
                    Point(19f, 0f),
                    Point(20f, 0f),
                    Point(21f, 0f),
                    Point(22f, 0f),
                    Point(23f, 60f),
                    Point(24f, 10f),
                    Point(25f, 0f),
                    Point(26f, 0f),
                    Point(27f, 0f),
                    Point(28f, 0f),
                    Point(29f, 0f),
                )

            DottedLinechart(pointsData)
        }

        item {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 16.dp
                ),
                text = "다가오는 금융 일정",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }

        items(5) {
            Row {
                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .width(64.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Danger100)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        color = White,
                        text = "6일",
                        fontSize = 16.sp
                    )
                }

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Column {
                    Text(
                        text = "넷플릭스 결제",
                        color = Grey700,
                        fontSize = 18.sp,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "4,950원 / 지출",
                        color = Grey400,
                        fontSize = 14.sp,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

                    .background(
                        color = CustomGrey100,
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(8.dp),
                        painter = painterResource(
                            id = R.drawable.ic_red_dot
                        ),
                        contentDescription = "red dot"
                    )

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    Text(
                        text = "3일 미만의 기간이 남음",
                        color = Grey600,
                        fontFamily = pretendard,
                        fontWeight = Medium,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                }

                //

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(8.dp),
                        painter = painterResource(
                            id = R.drawable.ic_orange_dot
                        ),
                        contentDescription = "orange dot"
                    )

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    Text(
                        text = "일주일 미만의 기간이 남음",
                        color = Grey600,
                        fontFamily = pretendard,
                        fontWeight = Medium,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                }

                //

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(8.dp),
                        painter = painterResource(
                            id = R.drawable.ic_green_dot
                        ),
                        contentDescription = "red dot"
                    )

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    Text(
                        text = "일주일 이상의 기간이 남음",
                        color = Grey600,
                        fontFamily = pretendard,
                        fontWeight = Medium,
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                }

                //

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
        }
    }
}

@Composable
private fun DottedLinechart(pointsData: List<Point>) {
    val steps = 30
    val xAxisData = AxisData.Builder()
        .axisStepSize(10.dp)
        .steps(pointsData.size - 1)
        .labelAndAxisLinePadding(10.dp)
        .startDrawPadding(30.dp)
        .startPadding(30.dp)
        .axisLineColor(Color.Transparent)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(10.dp)
        .startDrawPadding(30.dp)
        .startPadding(30.dp)
        .shouldDrawAxisLineTillEnd(false)
        .axisLineColor(Transparent)
        .build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(
                        lineType = LineType.Straight(isDotted = false),
                        color = Danger100
                    ),
                    shadowUnderLine = ShadowUnderLine(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.White,
                                Color.Transparent
                            )
                        ), alpha = 0.3f
                    ),
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        backgroundColor = Color.Yellow,
                        popUpLabel = { x, y ->
                            val xLabel = "1일 : ${x.toInt()} "
                            val yLabel = "y일 : ${String.format("%.2f", y)}"
                            "$xLabel \n $yLabel"
                        }
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}

//@RequiresApi(Build.VERSION_CODES.Q)
//@Preview
//@Composable
//fun Preview() {
//    HomeScreen(
//        navController = NavController(context = LocalContext.current),
//        data = MainUiState(),
//        onEvent = {
//
//        })
//}
