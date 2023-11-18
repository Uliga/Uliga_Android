package com.uliga.chart.bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.uliga.chart.bar.BarChartUtils.axisAreas
import com.uliga.chart.bar.BarChartUtils.forEachWithVerticalArea
import com.uliga.chart.bar.BarChartUtils.verticalBarDrawableArea
import com.uliga.chart.bar.renderer.bar.BarDrawer
import com.uliga.chart.bar.renderer.bar.SimpleBarDrawer
import com.uliga.chart.bar.renderer.label.HorizontalValueDrawer
import com.uliga.chart.bar.renderer.label.LabelDrawer
import com.uliga.chart.bar.renderer.xaxis.VerticalXAxisDrawer
import com.uliga.chart.bar.renderer.xaxis.XAxisDrawer
import com.uliga.chart.bar.renderer.yaxis.VerticalYAxisDrawer
import com.uliga.chart.bar.renderer.yaxis.YAxisDrawer
import com.uliga.chart.common.animation.chartAnimation

@Composable
fun VerticalBarChart(
    barChartData: BarChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = chartAnimation(),
    barDrawer: BarDrawer = SimpleBarDrawer(),
    xAxisDrawer: XAxisDrawer = VerticalXAxisDrawer(),
    yAxisDrawer: YAxisDrawer = VerticalYAxisDrawer(),
    labelDrawer: LabelDrawer = HorizontalValueDrawer()
) {
    val transitionAnimation = remember(barChartData.bars) { Animatable(initialValue = 0f) }

    LaunchedEffect(barChartData.bars) {
        transitionAnimation.animateTo(1f, animationSpec = animation)
    }

    val progress = transitionAnimation.value

    Canvas(modifier = modifier
        .fillMaxSize()
        .drawBehind {
            drawIntoCanvas { canvas ->
                val (xAxisArea, yAxisArea) = axisAreas(
                    drawScope = this,
                    totalSize = size,
                    xAxisDrawer = xAxisDrawer,
                    labelDrawer = labelDrawer
                )
                val barDrawableArea = verticalBarDrawableArea(xAxisArea)

                // Draw yAxis line.
                yAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = yAxisArea
                )

                // Draw xAxis line.
                xAxisDrawer.drawAxisLine(
                    drawScope = this,
                    canvas = canvas,
                    drawableArea = xAxisArea
                )

                // Draw each bar.
                barChartData.forEachWithVerticalArea(
                    this,
                    barDrawableArea,
                    progress,
                    labelDrawer
                ) { barArea, bar ->
                    barDrawer.drawBar(
                        drawScope = this,
                        canvas = canvas,
                        barArea = barArea,
                        bar = bar
                    )
                }
            }
        }
    ) {
        /**
         *  Typically we could draw everything here, but because of the lack of canvas.drawText
         *  APIs we have to use Android's `nativeCanvas` which seems to be drawn behind
         *  Compose's canvas.
         */
        drawIntoCanvas { canvas ->
            val (xAxisArea, yAxisArea) = axisAreas(
                drawScope = this,
                totalSize = size,
                xAxisDrawer = xAxisDrawer,
                labelDrawer = labelDrawer
            )
            val barDrawableArea = verticalBarDrawableArea(xAxisArea)

            barChartData.forEachWithVerticalArea(
                this,
                barDrawableArea,
                progress,
                labelDrawer
            ) { barArea, bar ->
                labelDrawer.drawLabel(
                    drawScope = this,
                    canvas = canvas,
                    label = bar.label,
                    barArea = barArea,
                    xAxisArea = xAxisArea
                )
            }

            yAxisDrawer.drawAxisLabels(
                drawScope = this,
                canvas = canvas,
                minValue = barChartData.minYValue,
                maxValue = barChartData.maxYValue,
                drawableArea = yAxisArea
            )
        }
    }
}