package com.uliga.chart.line

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.uliga.chart.common.animation.chartAnimation
import com.uliga.chart.line.LineChartUtils.calculateDrawableArea
import com.uliga.chart.line.LineChartUtils.calculateFillPath
import com.uliga.chart.line.LineChartUtils.calculateLinePath
import com.uliga.chart.line.LineChartUtils.calculateNewPointLocation
import com.uliga.chart.line.LineChartUtils.calculatePointLocation
import com.uliga.chart.line.LineChartUtils.calculateXAxisDrawableArea
import com.uliga.chart.line.LineChartUtils.calculateXAxisLabelsDrawableArea
import com.uliga.chart.line.LineChartUtils.calculateYAxisDrawableArea
import com.uliga.chart.line.LineChartUtils.withProgress
import com.uliga.chart.line.renderer.line.LineDrawer
import com.uliga.chart.line.renderer.line.LineShader
import com.uliga.chart.line.renderer.line.NoLineShader
import com.uliga.chart.line.renderer.line.SolidLineDrawer
import com.uliga.chart.line.renderer.xaxis.LineXAxisDrawer
import com.uliga.chart.line.renderer.xaxis.XAxisDrawer
import com.uliga.chart.line.renderer.yaxis.LineYAxisDrawer
import com.uliga.chart.line.renderer.yaxis.YAxisDrawer

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    linesChartData: List<LineChartData>,
    labels: List<String> = linesChartData.maxByOrNull { it.points.size }?.points?.map { it.label }
        ?: emptyList(),
    animation: AnimationSpec<Float> = chartAnimation(),
    lineShader: LineShader = NoLineShader,
    xAxisDrawer: XAxisDrawer = LineXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = LineYAxisDrawer(),
    horizontalOffset: Float = 5f,
) {
    check(horizontalOffset in 0f..25f) {
        "Horizontal offset is the % offset from sides, " +
                "and should be between 0%-25%"
    }

    val transitionAnimation = remember(linesChartData.forEach { it.points }) {
        mutableStateListOf(
            *linesChartData.map { Animatable(0f) }.toTypedArray()
        )
    }

    LaunchedEffect(linesChartData.forEach { it.points }) {
        repeat(linesChartData.size) { index ->
            transitionAnimation[index].snapTo(0f)
            transitionAnimation[index].animateTo(
                targetValue = 1f,
                animationSpec = animation
            )
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        drawIntoCanvas { canvas ->

            val yAxisDrawableArea = calculateYAxisDrawableArea(
                xAxisLabelSize = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisDrawableArea = calculateXAxisDrawableArea(
                yAxisWidth = yAxisDrawableArea.width,
                labelHeight = xAxisDrawer.requiredHeight(this),
                size = size
            )
            val xAxisLabelsDrawableArea = calculateXAxisLabelsDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                offset = horizontalOffset
            )
            val chartDrawableArea = calculateDrawableArea(
                xAxisDrawableArea = xAxisDrawableArea,
                yAxisDrawableArea = yAxisDrawableArea,
                size = size,
                offset = horizontalOffset
            )

            // Draw the X Axis line.
            xAxisDrawer.drawAxisLine(
                drawScope = this,
                drawableArea = xAxisDrawableArea,
                canvas = canvas
            )

            xAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = xAxisLabelsDrawableArea,
                labels = labels
            )

            // Draw the Y Axis line.
            yAxisDrawer.drawAxisLine(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea
            )

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                drawableArea = yAxisDrawableArea,
                minValue = linesChartData.minOf { it.minYValue },
                maxValue = linesChartData.maxOf { it.maxYValue }
            )



            linesChartData.forEachIndexed { index, lineChartData ->
                drawLine(
                    canvas = canvas,
                    lineChartData = lineChartData,
                    transitionAnimation = transitionAnimation[index],
                    lineDrawer = lineChartData.lineDrawer,
                    lineShader = lineShader,
                    chartDrawableArea = chartDrawableArea
                )
            }

        }
    }
}

private fun DrawScope.drawLine(
    lineDrawer: LineDrawer = SolidLineDrawer(),
    lineShader: LineShader = NoLineShader,
    canvas: Canvas,
    transitionAnimation: Animatable<Float, AnimationVector1D>,
    lineChartData: LineChartData,
    chartDrawableArea: Rect
) {

    // Draw the chart line.
    lineDrawer.drawLine(
        drawScope = this,
        canvas = canvas,
        linePath = calculateLinePath(
            drawableArea = chartDrawableArea,
            lineChartData = lineChartData,
            transitionProgress = transitionAnimation.value
        )
    )

    lineShader.fillLine(
        drawScope = this,
        canvas = canvas,
        fillPath = calculateFillPath(
            drawableArea = chartDrawableArea,
            lineChartData = lineChartData,
            transitionProgress = transitionAnimation.value
        )
    )

    val minPoint = lineChartData.points.minBy { it.value }
    val maxPoint = lineChartData.points.maxBy { it.value }

    lineChartData.points.forEachIndexed { index, point ->
        withProgress(
            index = index,
            lineChartData = lineChartData,
            transitionProgress = transitionAnimation.value
        ) {

            if (point == minPoint || point == maxPoint) {
                canvas.drawLine(
                    calculatePointLocation(
                        drawableArea = chartDrawableArea,
                        lineChartData = lineChartData,
                        point = point,
                        index = index
                    ),
                    calculateNewPointLocation(
                        drawableArea = chartDrawableArea,
                        lineChartData = lineChartData,
                        point = point,
                        index = index
                    ),
                    Paint().apply {
                        color = Color.Black
                    }
                )

                canvas.nativeCanvas.drawText("최대\n 4500원", calculateNewPointLocation(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    point = point,
                    index = index
                ).x, calculateNewPointLocation(
                    drawableArea = chartDrawableArea,
                    lineChartData = lineChartData,
                    point = point,
                    index = index
                ).y, android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    this.textSize = 12.dp.toPx()
                })
            }
        }
    }
}

