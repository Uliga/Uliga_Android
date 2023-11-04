package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun LoginButton(
    text: String,
    icon: Painter,
    imageSize: Dp,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp
            ),
        contentPadding = PaddingValues(
            vertical = 16.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        shape = UligaTheme.shapes.large,
        onClick = {
            onClick()
        }) {

        Image(
            modifier = Modifier
                .size(imageSize),
            painter = icon,
            contentDescription = null
        )

        HorizontalSpacer(width = 12.dp)

        Text(
            color = Grey700,
            text = text,
            style = UligaTheme.typography.body2
        )
    }

}