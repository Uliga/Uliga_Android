package com.uliga.app.view.finance

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.uliga.app.R
import com.uliga.app.ui.theme.CustomGray9B9B9B
import com.uliga.app.ui.theme.CustomGrayF9F9F9
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.main.MainUiEvent
import com.uliga.app.view.main.MainUiState
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
    navController: NavController,
    data: MainUiState,
    onEvent: (MainUiEvent) -> Unit
) {

    val context = LocalContext.current


    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )



    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .clickable {

                            },
                        text = "안세훈님의 가계부",
                        color = Color.Black,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }

        item {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 8.dp
                    ),
                text = "7월",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )

            HorizontalCalendar(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    ),
                state = state,
                dayContent = {
                    Day(
                        day = it,
                        onClick = {

                        }
                    )
                },
                monthHeader = {
                    DaysOfWeekTitle(daysOfWeek = daysOfWeek)
                }
            )
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 32.dp,
                        end = 32.dp,
                        top = 8.dp
                    ),
                text = "2월 17일 가계부",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

        item {

            TransactionItem()
            TransactionItem()
            TransactionItem()
            TransactionItem()

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

    }


}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            )
        }
    }
}

@Composable
fun Day(day: CalendarDay, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Color.Black else Color.Gray
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TransactionItem() {
    Spacer(
        modifier = Modifier.height(8.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp
            )
            .clip(RoundedCornerShape(6.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        Log.d("FinanceScreen", "onLongPress")
                    }
                )
            }
            .background(CustomGrayF9F9F9)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(52.dp)
                .weight(1f),
            painter = painterResource(
                id = R.drawable.ic_transaction
            ),
            contentDescription = null
        )

        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = "월세, 생활비 입금fdsdfdsfdsdsfsdfsd",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = "매월 28일 / 600,000원 / 수입sdfsdfsdsdfsdfds",
                color = CustomGray9B9B9B,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(16.dp)
                .weight(1f)
                .clickable {

                },
            painter = painterResource(
                id = R.drawable.ic_delete
            ),
            contentDescription = null
        )
    }
}