package com.uliga.app.view.analyze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.main.MainUiState
import com.uliga.chart.bar.HorizontalBarChart
import com.uliga.chart.bar.VerticalBarChart
import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import org.orbitmvi.orbit.compose.collectAsState
import java.lang.Math.abs
import java.time.LocalDate


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeByTimeScreen(viewModel: AnalyzeViewModel, mainUiState: MainUiState) {

    val state = viewModel.collectAsState().value

    val compareList = state.accountBookAnalyzeByMonthForCompare
    val currentDate = LocalDate.now()

    var differenceValueWithLastMonth = 0L
    if (compareList != null) {
        differenceValueWithLastMonth = compareList.compare[2].value - compareList.compare[1].value
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            viewModel.initialize()
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
                .fillMaxSize()
                .background(White),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 32.dp,
                            end = 32.dp,
                            top = 8.dp
                        ),
                    text = "${currentDate.monthValue}월 분석",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 32.dp,
                            end = 32.dp,
                            top = 8.dp
                        ),
                    text = "이번 달에는 지난 달보다 ${abs(differenceValueWithLastMonth)}원 ${if (differenceValueWithLastMonth >= 0) "더" else "덜"} 쓰셨어요!",
                    color = Grey500,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,

                    )

                HorizontalBarChartScreenContent(compareList)
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 32.dp,
                            end = 32.dp,
                            top = 8.dp
                        ),
                    text = "${currentDate.monthValue}월 주간별 분석",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 32.dp,
                            end = 32.dp,
                            top = 8.dp
                        ),
                    text = "이번 달 지출을 주간별로 확인해보세요!",
                    color = Grey500,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                VerticalBarChartScreenContent(
                    currentDate.monthValue,
                    state.accountBookAnalyzeRecordByWeek
                )
            }

        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState
        )
    }

    if(state.isLoading) {
        CircularProgress()
    }
}

@Composable
private fun HorizontalBarChartScreenContent(
    accountBookAnalyzeByMonthForCompare: AccountBookAnalyzeByMonthForCompare?
) {
    if (accountBookAnalyzeByMonthForCompare == null) return

    val compareList = accountBookAnalyzeByMonthForCompare.compare
    if (compareList.size != 3) return

    val barChartDataModel = HorizontalBarChartDataModel(
        recordCurrentMonthLabel = "${compareList[0].month}월 지출",
        recordCurrentMonthValue = compareList[0].value,
        recordBeforeOneMonthLabel = "${compareList[1].month}월 지출",
        recordBeforeOneMonthValue = compareList[1].value,
        recordBeforeTwoMonthLabel = "${compareList[2].month}월 지출",
        recordBeforeTwoMonthValue = compareList[2].value
    )

    Column(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 12.dp
        )
    ) {
        HorizonBarChartRow(barChartDataModel)
    }
}

@Composable
private fun VerticalBarChartScreenContent(
    currentMonth: Int,
    accountBookAnalyzeRecordByWeek: AccountBookAnalyzeRecordByWeek?
) {

    if(accountBookAnalyzeRecordByWeek == null) return

    val barChartDataModel = VerticalBarChartDataModel(
        currentMonth,
        accountBookAnalyzeRecordByWeek.weeklySums
    )

    Column(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 12.dp
        )
    ) {
        VerticalBarChartRow(barChartDataModel)
    }
}


@Composable
private fun HorizonBarChartRow(barChartDataModel: HorizontalBarChartDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(vertical = 16.dp)
    ) {
        HorizontalBarChart(
            barChartData = barChartDataModel.barChartData,
            labelDrawer = barChartDataModel.labelDrawer
        )
    }
}

@Composable
private fun VerticalBarChartRow(
    barChartDataModel: VerticalBarChartDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(vertical = 16.dp)
    ) {
        VerticalBarChart(
            barChartData = barChartDataModel.barChartData,
            labelDrawer = barChartDataModel.labelDrawer
        )
    }
}