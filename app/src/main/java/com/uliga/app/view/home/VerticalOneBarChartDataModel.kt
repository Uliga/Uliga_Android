package com.uliga.app.view.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.uliga.chart.bar.BarChartData
import com.uliga.chart.bar.BarChartData.Bar
import com.uliga.chart.bar.renderer.label.VerticalValueDrawer

class VerticalOneBarChartDataModel {
    private var colors = mutableListOf(
        Color(0XFFF44336),
        Color(0XFFE91E63),
        Color(0XFF9C27B0),
        Color(0XFF673AB7),
        Color(0XFF3F51B5),
        Color(0XFF03A9F4),
        Color(0XFF009688),
        Color(0XFFCDDC39),
        Color(0XFFFFC107),
        Color(0XFFFF5722),
        Color(0XFF795548),
        Color(0XFF9E9E9E),
        Color(0XFF607D8B)
    )
    var labelDrawer: VerticalValueDrawer by mutableStateOf(VerticalValueDrawer(drawLocation = VerticalValueDrawer.DrawLocation.XAxis))
        private set
    var barChartData by mutableStateOf(
        BarChartData(
            bars = listOf(
                Bar(
                    label = "",
                    value = randomValue() / 2,
                    color = randomColor()
                )
            )
        )
    )


    private fun randomValue(): Float = (100 * Math.random() + 25).toFloat()

    private fun randomColor(): Color {
        val randomIndex = (Math.random() * colors.size).toInt()
        val color = colors[randomIndex]
        colors.removeAt(randomIndex)

        return color
    }
}