package com.uliga.chart.bar.renderer.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.uliga.chart.bar.BarChartData

class SimpleBarDrawer : BarDrawer {
    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }

    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    ) {

        val left = barArea.left
        val top = barArea.top
        val right = barArea.right
        val bottom = barArea.bottom

        canvas.drawRoundRect(left, top, right, bottom, 10f, 10f, barPaint.apply {
            color = bar.color
        })
    }
}