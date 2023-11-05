package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.pretendard

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

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AddingButton(
    text: String,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .wrapContentSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                onClick = {
                    onClick()
                }
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(
                id = R.drawable.ic_adding_budget
            ),
            contentDescription = null
        )

        HorizontalSpacer(width = 4.dp)

        Text(
            text =text,
            color = Secondary,
            fontFamily = pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

}