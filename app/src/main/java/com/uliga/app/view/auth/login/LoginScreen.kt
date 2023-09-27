package com.uliga.app.view.auth.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uliga.app.R
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.auth.AuthRoute
import com.uliga.app.view.auth.AuthUiEvent
import com.uliga.app.view.auth.AuthUiState


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    data: AuthUiState,
    onEvent: (AuthUiEvent) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(

                modifier = Modifier
                    .size(72.dp),
                painter = painterResource(
                    id = R.drawable.uliga_logo
                ),
                contentDescription = "uliga logo"
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                color = Grey600,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 2,
                text = "우리가에 오신것을 환영합니다 \uD83D\uDE46\uD83C\uDFFB\u200D♀️"
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
                    .background(
                        color = CustomGrey100,
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {

                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                    textAlign = TextAlign.Start,
                    color = Grey600,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    text = "우리가란 '우리의 가계부' 의 줄임말으로, 공유 가계부 데스크톱 애플리케이션입니다.\n" +
                            "우리가로 가족, 룸메이트, 연인과 함께 지출관리를 해보세요!"
                )

            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "이메일",
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                val emailTextState = remember { mutableStateOf("") }

                TextField(
                    modifier = Modifier
                        .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    value = emailTextState.value,
                    onValueChange = {
                        emailTextState.value = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Primary,
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                navController.navigate(AuthRoute.SIGNUP.route)
                }) {
                Text(
                    color = Color.White,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    text = "이메일로 계속하기"
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .weight(5f),
                    color = Grey400,
                    thickness = 1.dp
                )

                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(2f),
                    color = Grey400,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    text = "또는",
                )

                Divider(
                    modifier = Modifier
                        .weight(5f),
                    color = Grey400,
                    thickness = 1.dp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = Color(0xFF777777),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    text = "SNS로 간편하게 로그인"
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(
                                id = R.drawable.ic_kakao
                            ),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            color = Grey400,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            text = "Kakao"
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(20.dp)
                    )

                    Column(
                        modifier = Modifier
                            .wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(
                                id = R.drawable.ic_google
                            ),
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            color = Grey400,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            text = "Google"
                        )
                    }

                }
            }
        }


    }



    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {




    }
}


