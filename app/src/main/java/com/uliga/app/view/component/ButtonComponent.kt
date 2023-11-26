package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey200
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun LoginButton(
    modifier: Modifier,
    text: String,
    icon: Painter,
    imageSize: Dp,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
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
    onClick: () -> Unit,
    testTag: String
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
            .testTag(testTag)
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
            text = text,
            color = Secondary,
            style = UligaTheme.typography.body6
        )
    }
}

@Composable
fun PositiveButton(
    modifier: Modifier = Modifier,
    text: String,
    contentPadding: PaddingValues,
    onClick: () -> Unit,
    textStyle: TextStyle = UligaTheme.typography.body11,
    testTag: String = ""
) {
    Button(
        modifier = modifier
            .testTag(testTag),
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Primary,
        ),
        shape = UligaTheme.shapes.large,
        onClick = { onClick() }) {
        Text(
            color = White,
            style = textStyle,
            text = text
        )
    }
}

@Composable
fun NegativeButton(
    modifier: Modifier = Modifier,
    text: String,
    contentPadding: PaddingValues,
    onClick: () -> Unit,
    textStyle: TextStyle = UligaTheme.typography.body11
) {
    Button(
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
        ),
        shape = UligaTheme.shapes.large,
        onClick = { onClick() }) {
        Text(
            color = Primary,
            style = textStyle,
            text = text
        )
    }
}

@Composable
fun DeclineButton(
    modifier: Modifier = Modifier,
    text: String,
    contentPadding: PaddingValues,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Grey200,
        ),
        shape = UligaTheme.shapes.large,
        onClick = { onClick() }) {
        Text(
            color = Primary,
            style = UligaTheme.typography.body11,
            text = text
        )
    }
}

@Composable
fun CheckButton(
    enabled: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    backgroundColor: ButtonColors,
    textColor: Color,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        colors = backgroundColor,
        shape = UligaTheme.shapes.large,
        onClick = { onClick() }
    ) {
        Text(
            color = textColor,
            style = UligaTheme.typography.body11,
            text = text
        )
    }
}
