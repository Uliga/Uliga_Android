package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.pretendard

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TextWithDotImage(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter,
    imageSize: Dp,
    textStyle: TextStyle,
    textColor: Color
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(imageSize),
            painter = painter,
            contentDescription = null
        )

        HorizontalSpacer(width = 8.dp)

        Text(
            text = text,
            color = textColor,
            style = textStyle
        )

    }

}
