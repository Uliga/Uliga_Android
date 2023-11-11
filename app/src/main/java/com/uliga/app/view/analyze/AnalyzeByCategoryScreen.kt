package com.uliga.app.view.analyze

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pieChartColor
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.main.MainUiState
import com.uliga.chart.pie.PieChart
import com.uliga.chart.pie.renderer.PieSliceDrawer
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryElement
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeByCategoryScreen(viewModel: AnalyzeViewModel, mainUiState: MainUiState) {

    val state = viewModel.collectAsState().value

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

                VerticalSpacer(height = 32.dp)

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "카테고리별 분석",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )

            }

            item {

                VerticalSpacer(height = 16.dp)

                PieChartScreenContent(state.accountBookAnalyzeRecordByMonthForCategory?.categories)

                VerticalSpacer(height = 16.dp)
            }

            items(state.accountBookAnalyzeRecordByMonthForCategory?.categories?.size ?: 0) { idx ->

                if (state.accountBookAnalyzeRecordByMonthForCategory?.categories == null) return@items

                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(
                                color = pieChartColor(state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].name),
                                shape = UligaTheme.shapes.small
                            )
                    )

                    HorizontalSpacer(width = 12.dp)

                    Text(
                        text = state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].name,
                        color = Grey500,
                        style = UligaTheme.typography.body12
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].value}원",
                        color = Grey500,
                        style = UligaTheme.typography.body12
                    )
                }
            }

            item {

                VerticalSpacer(height = 24.dp)

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "나의 고정 지출",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )
            }

            items(state.accountBookAnalyzeFixedRecordByMonth?.schedules?.size ?: 0) { idx ->

                val fixedRecordList =
                    state.accountBookAnalyzeFixedRecordByMonth?.schedules ?: return@items

                Row(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "${fixedRecordList[idx].day}일",
                        color = Grey500,
                        style = UligaTheme.typography.body12
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = fixedRecordList[idx].name,
                        color = Grey500,
                        style = UligaTheme.typography.body12
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${fixedRecordList[idx].value}원",
                        color = Grey500,
                        style = UligaTheme.typography.body12
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState
        )

    }

    if (state.isLoading) {
        CircularProgress()
    }
}

@Composable
private fun PieChartScreenContent(accountBookAnalyzeRecordByMonthForCategoryList: List<AccountBookAnalyzeRecordByMonthForCategoryElement>?) {

    if (accountBookAnalyzeRecordByMonthForCategoryList == null) return

    val pieChartDataModel =
        remember { PieChartDataModel(accountBookAnalyzeRecordByMonthForCategoryList) }

    Log.d("launchedEffect", pieChartDataModel.hashCode().toString())

    Column(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 12.dp
        )
    ) {
        PieChartRow(pieChartDataModel)
    }
}

@Composable
private fun PieChartRow(pieChartDataModel: PieChartDataModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(vertical = 8.dp)
    ) {
        PieChart(
            pieChartData = pieChartDataModel.pieChartData,
            sliceDrawer = PieSliceDrawer(
                sliceThickness = pieChartDataModel.sliceThickness
            )
        )
    }
}