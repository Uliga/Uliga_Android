package com.uliga.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TopDownToast(
    toastYOffset: Float,
    toastMessage: String
) {
    Box(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .offset(
                    y = toastYOffset.dp
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Primary,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                text = toastMessage
            )
        }
    }
}

const val TOAST_START_POSITION = -100f

const val TOAST_END_POSITION = 25f

const val TOAST_DURATION_MILLIS = 1500

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ToastAnimation(
    yOffset: Float,
    toastMessage: String
) {
    Box(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.TopCenter)
                .offset(
                    y = yOffset.dp
                )
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.White)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = Primary,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                text = toastMessage
            )
        }
    }
}