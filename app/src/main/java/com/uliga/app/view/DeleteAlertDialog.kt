package com.uliga.app.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DeleteAlertDialog(
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
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 2,
                text = title
            )

            Spacer(
                Modifier.height(24.dp)
            )

            Text(
                color = Grey500,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                maxLines = 2,
                text = subTitle
            )

            Spacer(
                Modifier.height(60.dp)
            )

            Row {
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = White,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        onDismissRequest
                    }) {


                    Text(
                        color = Primary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        text = "취소"
                    )
                }

                Button(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Primary,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        onDismissRequest
                    }) {


                    Text(
                        color = Color.White,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        text = "삭제"
                    )
                }
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
        title = "안세훈님의 가계부 삭제",
        subTitle = "정말 안세훈님의 가계부를 삭제하시겠어요?"
    )
}