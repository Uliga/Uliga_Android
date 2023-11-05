package com.uliga.app.view.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.uliga.app.ui.theme.Danger100
import com.uliga.app.utils.Constant
import com.uliga.app.view.home.LineChartDataModel
import com.uliga.chart.line.LineChart
import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDay

@Composable
fun HorizontalLineIndicator(
    animateNumber: Float
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constant.indicatorHeight)
            .padding(horizontal = Constant.indicatorPadding)
    ) {

        drawLine(
            color = Color.LightGray.copy(alpha = 0.3f),
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f)
        )

        val progress = (animateNumber) * size.width

        drawLine(
            color = Danger100,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = progress, y = 0f)
        )

    }
}

@Composable
fun ContinuousLineChart(
    accountBookAnalyzeRecordByDay: AccountBookAnalyzeRecordByDay?
) {

    if (accountBookAnalyzeRecordByDay == null) return

    val lineChartDataModel = LineChartDataModel(accountBookAnalyzeRecordByDay.records)

    Column(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = 8.dp
        )
    ) {
        Box(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        ) {

            LineChart(
                linesChartData = listOf(lineChartDataModel.lineChartData),
                horizontalOffset = lineChartDataModel.horizontalOffset,
            )
        }
    }
}