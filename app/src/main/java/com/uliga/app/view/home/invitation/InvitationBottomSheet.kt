package com.uliga.app.view.home.invitation

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey300
import com.uliga.app.ui.theme.LightBlue
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.budget.BudgetSettingBottomSheetCompose
import com.uliga.app.view.main.MainActivity


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun InvitationBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest
        })
    {

        InvitationBottomSheetCompose()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun InvitationBottomSheetCompose() {
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
                text = "새로운 가계부 초대",
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
                modifier = Modifier
                    .clickable {

                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        modifier = Modifier
                            .size(52.dp),
                        painter = painterResource(
                            id = R.drawable.ic_invitation_logo
                        ),
                        contentDescription = "uliga logo"
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "안세훈님의 초대",
                            color = Color(0xFF9B9B9B),
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Text(
                            text = "첫번째 가계부",
                            color = Color(0xFF464656),
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        modifier = Modifier
                            .wrapContentSize(),
                        contentPadding = PaddingValues(
                            vertical = 12.dp,
                            horizontal = 12.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFE9EEFE),
                        ),
                        shape = RoundedCornerShape(7.dp),
                        onClick = {

                        }) {
                        Text(
                            color = Primary,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            text = "거절"
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        modifier = Modifier
                            .wrapContentSize(),
                        contentPadding = PaddingValues(
                            vertical = 12.dp,
                            horizontal = 12.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Primary,
                        ),
                        shape = RoundedCornerShape(7.dp),
                        onClick = {

                        }) {
                        Text(
                            color = Color.White,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            text = "수락"
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                androidx.compose.material.Divider(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        ),
                    color = Color(0xFFE3E3E3),
                    thickness = 1.dp
                )

            }
        }



    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Preview() {
    InvitationBottomSheetCompose()
}
