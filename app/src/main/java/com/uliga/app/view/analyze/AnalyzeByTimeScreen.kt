package com.uliga.app.view.analyze

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.chart.bar.HorizontalBarChart
import com.uliga.chart.bar.VerticalBarChart
import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import org.orbitmvi.orbit.compose.collectAsState
import java.lang.Math.abs
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeByTimeScreen(viewModel: AnalyzeViewModel) {

    val state = viewModel.collectAsState().value

    val compareList = state.accountBookAnalyzeByMonthForCompare
    val currentDate = LocalDate.now()

    var differenceValueWithLastMonth = 0L
    if(compareList != null) {
        differenceValueWithLastMonth = compareList.compare[2].value - compareList.compare[1].value
    }

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
                color =Grey700,
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
                text = "이번 달에는 지난 달보다 ${abs(differenceValueWithLastMonth)}원 ${if(differenceValueWithLastMonth >= 0) "더" else "덜"} 쓰셨어요!",
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
                text = "주간별 분석",
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
                text = "이번 달에는 살장힌 예산보다 ㅇ러ㅐㄴ어래너애런앨!",
                color = Grey500,
                fontFamily = pretendard,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,

                )


            VerticalBarChartScreenContent()
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
                text = "4월 고정 지출",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )


        }
    }
}

@Composable
private fun HorizontalBarChartScreenContent(
    accountBookAnalyzeByMonthForCompare: AccountBookAnalyzeByMonthForCompare?
) {
    if(accountBookAnalyzeByMonthForCompare == null) return

    val compareList = accountBookAnalyzeByMonthForCompare.compare
    if(compareList.size != 3) return

    val barChartDataModel = HorizontalBarChartDataModel(
        recordCurrentMonthLabel = "${compareList[0].month}월 지출",
        recordCurrentMonthValue = compareList[0].value,
        recordBeforeOneMonthLabel = "${compareList[1].month}월 지출",
        recordBeforeOneMonthValue =  compareList[1].value,
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
private fun VerticalBarChartScreenContent() {
    val barChartDataModel = VerticalBarChartDataModel()

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
private fun VerticalBarChartRow(barChartDataModel: VerticalBarChartDataModel) {
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