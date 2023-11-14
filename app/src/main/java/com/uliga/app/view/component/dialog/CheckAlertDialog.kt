package com.uliga.app.view.component.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CheckAlertDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    title: String,
    subTitle: String
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(36.dp))
                .background(White)
                .padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                )
        ) {
            Text(
                color = Grey600,
                style = UligaTheme.typography.body2,
                maxLines = 2,
                text = title
            )

            VerticalSpacer(height = 24.dp)

            Text(
                color = Grey500,
                style = UligaTheme.typography.body14,
                maxLines = 2,
                text = subTitle
            )

            VerticalSpacer(height = 60.dp)

            Row {
                Spacer(modifier = Modifier.weight(1f))

                PositiveButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    text = "확인",
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    onClick = {
                        onDismissRequest()
                    },
                    textStyle = UligaTheme.typography.body14
                )
            }
        }
    }

}