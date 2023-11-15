package com.uliga.app.view.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.uliga.app.ui.theme.Danger100

@Composable
fun HorizontalLineIndicator(
    modifier: Modifier = Modifier,
    animateNumber: Float
) {
    Canvas(
        modifier = modifier
    ) {

        drawLine(
            color = Color.LightGray.copy(alpha = 0.3f),
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f)
        )

        val progress = (animateNumber) * size.width

        drawLine(
            color = Danger100,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = progress, y = 0f)
        )

    }
}