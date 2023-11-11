package com.uliga.app.view.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White

@Composable
fun AccountBookItem(
    accountBookName: String,
    accountBookColor: Color,
    accountBookImageAlpha: Float,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                onClick = {
                    onClick()
                }
            )
            .border(
                width = 1.dp,
                color = accountBookColor,
                shape = UligaTheme.shapes.small
            )
            .background(White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = accountBookName,
            color = accountBookColor,
            style = UligaTheme.typography.body3,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(
            Modifier.weight(1f)
        )

        Image(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
                .alpha(accountBookImageAlpha)
                .size(40.dp),
            painter = painterResource(
                id = R.drawable.ic_account_book_select
            ),
            contentDescription = "uliga logo"
        )
    }

}