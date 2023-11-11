package com.uliga.app.view

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.component.NegativeButton
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DeleteAlertDialog(
    onDismissRequest: () -> Unit,
    onDeleteRequest: () -> Unit,
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
                .clip(UligaTheme.shapes.xlarge)
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

                NegativeButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    text = "취소",
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    onClick = { onDismissRequest() },
                    textStyle = UligaTheme.typography.body14
                )
//                Button(
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .padding(horizontal = 4.dp),
//                    contentPadding = PaddingValues(
//                        vertical = 4.dp
//                    ),
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = White,
//                    ),
//                    shape = RoundedCornerShape(10.dp),
//                    onClick = {
//                        onDismissRequest()
//                    }) {
//
//
//                    Text(
//                        color = Primary,
//                        fontFamily = pretendard,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 12.sp,
//                        text = "취소"
//                    )
//                }

                PositiveButton(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    text = "삭제",
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    onClick = { onDeleteRequest() },
                    textStyle = UligaTheme.typography.body14
                )
//                Button(
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .padding(horizontal = 4.dp),
//                    contentPadding = PaddingValues(
//                        vertical = 4.dp
//                    ),
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Primary,
//                    ),
//                    shape = RoundedCornerShape(10.dp),
//                    onClick = {
//                        onDeleteRequest()
//                    }) {
//
//
//                    Text(
//                        color = Color.White,
//                        fontFamily = pretendard,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 12.sp,
//                        text = "삭제"
//                    )
//                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Preview() {
    DeleteAlertDialog(
        onDismissRequest = {},
        onDeleteRequest = {},
        title = "안세훈님의 가계부 삭제",
        subTitle = "정말 안세훈님의 가계부를 삭제하시겠어요?"
    )
}