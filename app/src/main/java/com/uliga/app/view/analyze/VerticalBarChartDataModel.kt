package com.uliga.app.view.analyze

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.uliga.app.ui.theme.Secondary
import com.uliga.chart.bar.BarChartData
import com.uliga.chart.bar.BarChartData.Bar
import com.uliga.chart.bar.renderer.label.VerticalValueDrawer
import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeWeeklySum

class VerticalBarChartDataModel(
    currentMonth: Int,
    accountBookAnalyzeRecordByWeekList: List<AccountBookAnalyzeWeeklySum>
) {

    var labelDrawer: VerticalValueDrawer by mutableStateOf(VerticalValueDrawer(drawLocation = VerticalValueDrawer.DrawLocation.XAxis))
        private set
    var barChartData by mutableStateOf(
        BarChartData(
            bars = listOf()
        )
    )

    init {
        val barList = mutableListOf<Bar>()
        accountBookAnalyzeRecordByWeekList.forEachIndexed { index, accountBookAnalyzeWeeklySum ->
            barList.add(
                Bar(
                    label = "${index+1}주차",
                    color = Secondary,
                    value = accountBookAnalyzeWeeklySum.value.toFloat()
                )
            )
        }
        barChartData = BarChartData(barList)
    }





}