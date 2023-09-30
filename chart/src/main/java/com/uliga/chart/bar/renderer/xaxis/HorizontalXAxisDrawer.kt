package com.uliga.chart.bar.renderer.xaxis

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class HorizontalXAxisDrawer(
  private val axisLineThickness: Dp = 1.dp,
  private val axisLineColor: Color = Color.Black
) : XAxisDrawer {
  private val axisLinePaint = Paint().apply {
    isAntiAlias = true
    color = axisLineColor
    style = PaintingStyle.Stroke
  }

  override fun requiredHeight(drawScope: DrawScope): Float = with(drawScope) {
    (3f / 2f) * axisLineThickness.toPx()
  }

  override fun drawAxisLine(drawScope: DrawScope, canvas: Canvas, drawableArea: Rect) {

  }
}