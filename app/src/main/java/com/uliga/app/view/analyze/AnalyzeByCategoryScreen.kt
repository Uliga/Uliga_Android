package com.uliga.app.view.analyze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeByCategoryScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 8.dp
                    ),
                text = "카테고리별 분석",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

        }
    }
}