package com.uliga.app.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.uliga.chart.line.LineChartData
import com.uliga.chart.line.LineChartData.*
import com.uliga.chart.line.renderer.line.SolidLineDrawer

class LineChartDataModel {
    var lineChartData by mutableStateOf(
        LineChartData(
            points = listOf(
                Point(randomYValue(), "Label1"),
                Point(randomYValue(), "Label2"),
                Point(randomYValue(), "Label3"),
                Point(randomYValue(), "Label4"),
                Point(randomYValue(), "Label5"),
                Point(randomYValue(), "Label6"),
                Point(randomYValue(), "Label7")
            ),
            lineDrawer = SolidLineDrawer(),
        )
    )

    var horizontalOffset by mutableStateOf(5f)

    private fun randomYValue(): Float = (100f * Math.random()).toFloat() + 45f

}