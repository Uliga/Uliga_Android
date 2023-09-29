package com.uliga.app.view.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleAlertBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest
        })
    {

        ScheduleAlertBottomSheetCompose()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ScheduleAlertBottomSheetCompose() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 4.dp
                ),
                text = "새로운 금융 일정 알림",
                color = Color.Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        items(5) {

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "안세훈",
                        color = Color(0xFF464656),
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        text = "님 의 금융 일정 할당",
                        color = Color(0xFF464656),
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "월세, 생활비 입금",
                    color = Color(0xFF464656),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "매달 28일 / 600,000원 / 지출",
                    color = Color(0xFF9B9B9B),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "윤채현 100원 ∙  나연경 100,000원 ∙ 이시원 590,000원",
                    color = Primary,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Divider(
                    modifier = Modifier,
                    color = Color(0xFFE3E3E3),
                    thickness = 1.dp
                )

            }
        }


    }
}
