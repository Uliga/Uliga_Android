package com.uliga.app.view.analyze

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.uliga.app.ui.theme.pieChartColor
import com.uliga.chart.pie.PieChartData
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategoryElement

class PieChartDataModel(
    accountBookAnalyzeRecordByMonthForCategoryList: List<AccountBookAnalyzeRecordByMonthForCategoryElement>
) {

    var sliceThickness by mutableStateOf(25f)
    var pieChartData by mutableStateOf(
        PieChartData(
            slices = emptyList()
        )
    )

    init {
        val list = mutableListOf<PieChartData.Slice>()
        accountBookAnalyzeRecordByMonthForCategoryList.forEach {
            list.add(
                PieChartData.Slice(
                    value = it.value.toFloat(),
                    color = pieChartColor(it.name)
                )
            )
        }

        pieChartData = PieChartData(list)
    }
}