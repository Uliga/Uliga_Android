package com.uliga.app.view.finance

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.uliga.app.ui.theme.LightBlue
import com.uliga.app.ui.theme.Secondary
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.accountBook.input.AccountBookForInputActivity
import com.uliga.app.view.accountBook.selection.AccountBookSelectionBottomSheet
import com.uliga.app.view.main.MainUiState
import com.uliga.app.view.schedule.ScheduleBottomSheet
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FinanceScreen(
    navController: NavController,
) {

    val context = LocalContext.current

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    val daysOfWeek = remember { daysOfWeek() }

    val coroutineScope = rememberCoroutineScope()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

//    val accountBookSelectionSheetState = rememberModalBottomSheetState(
//        ModalBottomSheetValue.Expanded,
//        skipHalfExpanded = true
//    )
//
//    LaunchedEffect(data.showAccountBookSelectionBottomSheet) {
//        if(data.showAccountBookSelectionBottomSheet == true) {
//            accountBookSelectionSheetState.show()
//        } else {
//            accountBookSelectionSheetState.hide()
//        }
//    }

    val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isSheetOpen) {
        AccountBookSelectionBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        )
    }


    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        showSettingDropDownMenu(
            showDialog = showDialog,
        )
    }

    val accountBookForInputSheet = androidx.compose.material3.rememberModalBottomSheetState()
//    var isAccountBookForInputSheetOpen by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    if (isAccountBookForInputSheetOpen) {
//        AccountBookForInputScreen(
//            sheetState = accountBookForInputSheet,
//            onDismissRequest = {
//                isAccountBookForInputSheetOpen = false
//            }
//        )
//    }

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
                                isSheetOpen = true
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
                    IconButton(onClick = {
                        showDialog = true
                    }) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp
                    ),
                    text = "9월 23일",
                    color = Grey700,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.weight(1f))


                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            val intent = Intent(context, AccountBookForInputActivity::class.java)
                            context.startActivity(intent)

                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(
                            id = R.drawable.ic_adding_budget
                        ),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "가계부 추가하기",
                        color = Secondary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
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


//    if (!accountBookSelectionSheetState.isVisible) {
//        AccountBookSelectionBottomSheet(
//            bottomSheetState = accountBookSelectionSheetState,
//            sheetContent = {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                ) {
//                    item {
//                        Text(
//                            modifier = Modifier
//                                .clickable {
//
//                                },
//                            text = "가계부 목록",
//                            color = Color.Black,
//                            fontFamily = pretendard,
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp,
//                            overflow = TextOverflow.Ellipsis,
//                            maxLines = 1
//                        )
//                    }
//
//                }
//            },
//            content = {}
//        )
//    }
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
            .background(CustomGrayF9F9F9),
        verticalAlignment = Alignment.CenterVertically
    ) {

//        Box(
//            modifier = Modifier
//                .padding(16.dp)
//                .size(52.dp)
//                .border(width = 1.dp, color = Success100, shape = RoundedCornerShape(5.dp))
//                .weight(1f)
//                .background(Success100)
//                ,
//        ) {
//            Text(
//                modifier = Modifier
//                    .align(Alignment.Center),
//                text = "지출",
//                color = White,
//                fontFamily = pretendard,
//                fontWeight = FontWeight.SemiBold,
//                fontSize = 16.sp,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 1
//            )
//        }

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(52.dp),
                painter = painterResource(
                    id = R.drawable.ic_transaction
                ),
                contentDescription = null
            )

            Text(
                text = "지출",
                color = LightBlue,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }


        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = "식비 / sdf",
                color = Grey700,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = "1,000원 / 현금 / by. 안세훈",
                color = CustomGray9B9B9B,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = "메모메모메모메모메모메모메모",
                color = LightBlue,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
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

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showSettingDropDownMenu(
    showDialog: Boolean,
) {
    val scheduleSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isScheduleSheetStateOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isScheduleSheetStateOpen) {
//        ScheduleBottomSheet(
//            sheetState = scheduleSheetState,
//            onDismissRequest = {
//                isScheduleSheetStateOpen = false
//            }
//        )
    }

    var expanded by remember { mutableStateOf(true) }
    val items = listOf(
        "금융 일정 추가하기", "가계부 작성하기"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    Color.White
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
//                    showDialog = false

                    when (index) {
                        0 -> {
                            isScheduleSheetStateOpen = true
                        }
                    }

                }) {
                    Text(text = s)
                    if (index < 3) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }


    }
}