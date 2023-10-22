package com.uliga.app.view.analyze

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.Secondary
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

    fun pieChartColor(categoryName: String): Color {
        when(categoryName) {
            "\uD83C\uDF7D️ 식비" -> {
                return Primary
            }
            "\uD83C\uDF59 편의점,마트,잡화" -> {
                return Secondary
            }
            else -> {
                return Color.Black
            }
        }
    }

}