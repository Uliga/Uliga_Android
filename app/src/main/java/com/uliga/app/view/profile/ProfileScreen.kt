package com.uliga.app.view.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uliga.app.R
import com.uliga.app.ui.theme.Black
import com.uliga.app.ui.theme.Grey300
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.main.MainUiState

@SuppressLint("NewApi")
@Composable
fun ProfileScreen(
    navController: NavController,
    mainUiState: MainUiState,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {

            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 32.dp,
                    bottom = 16.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp),
                    painter = painterResource(
                        id = R.drawable.ic_my
                    ),
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Column {
                    Text(
                        modifier = Modifier.padding(
                            end = 12.dp
                        ),
                        text = "안세훈님",
                        color = Black,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )

                    Text(
                        modifier = Modifier.padding(
                            end = 12.dp
                        ),
                        text = "ansehoon1999@gmail.com",
                        color = Black,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }

            }
        }


        item {

            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                text = "설정",
                color = Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                    .clickable {

                    },
                text = "기본 정보",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                    .clickable {

                    },
                text = "가계부 정보",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    ),
                text = "버전 정보",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

        }

        item {

            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
                text = "계정",
                color = Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                    .clickable {

                    },
                text = "로그아웃",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
                    .clickable {

                    },
                text = "탈퇴하기",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Divider(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    ),
                color = Grey300,
                thickness = 1.dp
            )

        }
    }
}