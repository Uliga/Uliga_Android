package com.uliga.chart.bar.renderer.label

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.uliga.chart.bar.renderer.label.VerticalValueDrawer.DrawLocation.Inside
import com.uliga.chart.bar.renderer.label.VerticalValueDrawer.DrawLocation.Outside
import com.uliga.chart.bar.renderer.label.VerticalValueDrawer.DrawLocation.XAxis
import com.uliga.chart.common.utils.toLegacyInt

class VerticalValueDrawer(
    private val drawLocation: DrawLocation = Inside,
    private val labelTextSize: TextUnit = 12.sp,
    private val labelTextColor: Color = Color.Gray
) : LabelDrawer {
    private val _labelTextArea: Float? = null
    private val paint = android.graphics.Paint().apply {
        this.textAlign = android.graphics.Paint.Align.CENTER
        this.color = labelTextColor.toLegacyInt()
    }

    override fun requiredAboveBarHeight(drawScope: DrawScope): Float = when (drawLocation) {
        Outside -> (3f / 2f) * labelTextHeight(drawScope)
        Inside,
        XAxis -> 0f
    }

    override fun requiredXAxisHeight(drawScope: DrawScope): Float = when (drawLocation) {
        XAxis -> labelTextHeight(drawScope)
        Inside,
        Outside -> 0f
    }

    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) = with(drawScope) {
        val xCenter = barArea.left + (barArea.width / 2)

        val yCenter = when (drawLocation) {
            Inside -> (barArea.top + barArea.bottom) / 2
            Outside -> (barArea.top) - labelTextSize.toPx() / 2
            XAxis -> barArea.bottom + labelTextHeight(drawScope)
        }

        canvas.nativeCanvas.drawText(label, xCenter, yCenter, paint(drawScope))
        canvas.nativeCanvas.drawText(
            label,
            xCenter,
            yCenter + labelTextHeight(drawScope),
            paint(drawScope)
        )
    }

    override fun drawLabelWithValue(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        value: Float,
        barArea: Rect,
        xAxisArea: Rect
    ) = with(drawScope) {
        val xCenter = barArea.left + (barArea.width / 2)

        val yCenter = when (drawLocation) {
            Inside -> (barArea.top + barArea.bottom) / 2
            Outside -> (barArea.top) - labelTextSize.toPx() / 2
            XAxis -> barArea.bottom + labelTextHeight(drawScope)
        }

        canvas.nativeCanvas.drawText("${value.toInt()}원", xCenter, yCenter, paint(drawScope))
        canvas.nativeCanvas.drawText(
            label,
            xCenter,
            yCenter + labelTextHeight(drawScope),
            paint(drawScope)
        )
    }

    private fun labelTextHeight(drawScope: DrawScope) = with(drawScope) {
        _labelTextArea ?: ((3f / 2f) * labelTextSize.toPx())
    }

    private fun paint(drawScope: DrawScope) = with(drawScope) {
        paint.apply {
            this.textSize = labelTextSize.toPx()
        }
    }

    enum class DrawLocation {
        Inside,
        Outside,
        XAxis
    }
}