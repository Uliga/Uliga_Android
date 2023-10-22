package com.uliga.app.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.uliga.chart.line.LineChartData
import com.uliga.chart.line.LineChartData.*
import com.uliga.chart.line.renderer.line.SolidLineDrawer
import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordDaily

class LineChartDataModel(
    val recordList: List<AccountBookAnalyzeRecordDaily>
) {
    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
//                Point(randomYValue(), "Label1"),
//                Point(randomYValue(), "Label2"),
//                Point(randomYValue(), "Label3"),
//                Point(randomYValue(), "Label4"),
//                Point(randomYValue(), "Label5"),
//                Point(randomYValue(), "Label6"),
//                Point(randomYValue(), "Label7")
            ),
            lineDrawer = SolidLineDrawer(),
        )
    )

    var horizontalOffset by mutableStateOf(5f)

    init {
        val pointList = mutableListOf<Point>()
        recordList.forEach { record ->
            pointList.add(Point(record.value.toFloat(), record.day.toString()))
        }
        lineChartData = LineChartData(
            points = pointList,
            lineDrawer = SolidLineDrawer()
        )
    }
}