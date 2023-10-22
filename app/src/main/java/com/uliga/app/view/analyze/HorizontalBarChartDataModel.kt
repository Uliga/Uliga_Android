package com.uliga.app.view.analyze

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Success200
import com.uliga.chart.bar.BarChartData
import com.uliga.chart.bar.BarChartData.Bar
import com.uliga.chart.bar.renderer.label.HorizontalValueDrawer

class HorizontalBarChartDataModel(
    recordCurrentMonthLabel: String,
    recordCurrentMonthValue: Long,
    recordBeforeOneMonthLabel: String,
    recordBeforeOneMonthValue: Long,
    recordBeforeTwoMonthLabel: String,
    recordBeforeTwoMonthValue: Long
) {
    var labelDrawer: HorizontalValueDrawer by mutableStateOf(HorizontalValueDrawer(drawLocation = HorizontalValueDrawer.DrawLocation.XAxis))
        private set
    var barChartData by mutableStateOf(
        BarChartData(
            bars = listOf(
                Bar(
                    label = recordBeforeTwoMonthLabel,
                    value = recordBeforeTwoMonthValue.toFloat(),
                    color = Color(0XFF03A9F4)
                ),
                Bar(
                    label = recordBeforeOneMonthLabel,
                    value = recordBeforeOneMonthValue.toFloat(),
                    color = Success200
                ),
                Bar(
                    label = recordCurrentMonthLabel,
                    value = recordCurrentMonthValue.toFloat(),
                    color = Grey500
                )
            )
        )
    )
}