package com.uliga.app.view.analyze

import android.os.Build
import android.widget.Space
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pieChartColor
import com.uliga.app.ui.theme.pretendard
import com.uliga.chart.pie.PieChart
import com.uliga.chart.pie.renderer.PieSliceDrawer
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategory
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryElement
import org.orbitmvi.orbit.compose.collectAsState

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeByCategoryScreen(viewModel: AnalyzeViewModel) {

    val state = viewModel.collectAsState().value

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
                text = "카테고리별 분석",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

        }

        item {
            PieChartScreenContent(state.accountBookAnalyzeRecordByMonthForCategory?.categories)
        }

        items(state.accountBookAnalyzeRecordByMonthForCategory?.categories?.size ?: 0) {idx ->

            if(state.accountBookAnalyzeRecordByMonthForCategory?.categories == null) return@items

            Row {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = pieChartColor(state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].name),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].name,
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${state.accountBookAnalyzeRecordByMonthForCategory.categories[idx].value}원",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
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
                text = "나의 고정 지출",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        items(state.accountBookAnalyzeFixedRecordByMonth?.schedules?.size ?: 0) { idx ->

            val fixedRecordList = state.accountBookAnalyzeFixedRecordByMonth?.schedules ?: return@items

            Row {
                Text(
                    text = "${fixedRecordList[idx].day}일",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = fixedRecordList[idx].name,
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${fixedRecordList[idx].value}원",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }

        }


    }
}

@Composable
private fun PieChartScreenContent(accountBookAnalyzeRecordByMonthForCategoryList: List<AccountBookAnalyzeRecordByMonthForCategoryElement>?) {

    if(accountBookAnalyzeRecordByMonthForCategoryList == null) return

    val pieChartDataModel = remember { PieChartDataModel(accountBookAnalyzeRecordByMonthForCategoryList) }

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