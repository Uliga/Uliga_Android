package com.uliga.app.view.analyze

import android.os.Build
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
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