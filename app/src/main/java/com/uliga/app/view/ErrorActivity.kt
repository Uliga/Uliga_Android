package com.uliga.app.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ErrorActivity: ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UligaApplicationTheme {


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_uliga_logo_error
                        ),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 32.dp
                            ),
                        text = "예상치 못한 에러가 발생했습니다.",
                        color = Grey700,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp
                            ),
                        text = "원하시는 페이지로 이동할 수 없습니다.\n이전 페이지로 이동해주세요.",
                        color = Grey500,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3
                    )

                    Text(
                        modifier = Modifier
                            .width(300.dp)
                            .padding(
                                vertical = 8.dp,
                                horizontal = 32.dp
                            )
                            .clip(RoundedCornerShape(6.dp))
                            .background(Primary)
                            .clickable {

                            }
                            ,
                        text = "홈으로",
                        color = White,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        maxLines = 1
                    )
                }

            }
        }
    }
}