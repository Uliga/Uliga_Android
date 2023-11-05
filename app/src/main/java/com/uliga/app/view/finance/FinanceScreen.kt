package com.uliga.app.view.finance

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.uliga.app.TopDownToast
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.DeleteAlertDialog
import com.uliga.app.view.accountBook.input.AccountBookForInputActivity
import com.uliga.app.view.accountBook.selection.AccountBookSelectionBottomSheet
import com.uliga.app.view.component.AddingButton
import com.uliga.app.view.component.Day
import com.uliga.app.view.component.DaysOfWeekTitle
import com.uliga.app.view.component.TransactionItem
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.main.MainUiState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate
import java.time.YearMonth

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FinanceScreen(
    navController: NavController,
    mainUiState: MainUiState,
    viewModel: FinanceViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    viewModel.initializeBaseInfo(
        id = mainUiState.id,
        currentAccountInfo = mainUiState.currentAccountInfo,
        member = mainUiState.member
    )

    /**
     * Date (need refactoring)
     */

    val currentDate = LocalDate.now()

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val daysOfWeek = remember { daysOfWeek() }

    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    val selectedDate =
        remember { mutableStateOf("${currentDate.year}년 ${currentDate.monthValue}월 ${currentDate.dayOfMonth}일") }

    LaunchedEffect(key1 = calendarState.firstVisibleMonth) {
        viewModel.getAccountBookMonthTransaction(
            calendarState.firstVisibleMonth.yearMonth.year,
            calendarState.firstVisibleMonth.yearMonth.monthValue
        )

    }

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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    viewModel.getAccountBookMonthTransaction(
                        calendarState.firstVisibleMonth.yearMonth.year,
                        calendarState.firstVisibleMonth.yearMonth.monthValue
                    )
                    viewModel.getAccountBookDayTransaction(
                        calendarState.firstVisibleMonth.yearMonth.year,
                        calendarState.firstVisibleMonth.yearMonth.monthValue,
                        selectedDate.value.split("월 ")[1].replace("일", "").toInt()
                    )

                }
            }
        })

    var deleteAlertDialogVisibleState by remember {
        mutableStateOf(false)
    }

    var selectedDeleteItemId by remember {
        mutableStateOf(0L)
    }

    if (deleteAlertDialogVisibleState) {
        DeleteAlertDialog(
            onDismissRequest = {
                deleteAlertDialogVisibleState = false
            },
            onDeleteRequest = {
                viewModel.deleteAccountBookDayTransaction(selectedDeleteItemId)
                deleteAlertDialogVisibleState = false
            },
            title = "거래 내역 삭제",
            subTitle = "정말 선택한 거래 내역을 삭제하시겠어요?"
        )
    }

    /**
     * Toast Message
     */

    var isToastAnimating by remember {
        mutableStateOf(false)
    }

    var toastMessage by remember {
        mutableStateOf("")
    }

    val toastYOffset by animateFloatAsState(
        targetValue = if (isToastAnimating) 25f else -100f,
        animationSpec = tween(durationMillis = 1500),
        finishedListener = { endValue ->
            if (endValue == 25f) {
                isToastAnimating = false
            }
        },
        label = ""
    )

    /**
     * SideEffect
     */

    viewModel.collectSideEffect {
        handleSideEffect(
            sideEffect = it,
            context = context,
            onDismissDeleteAlert = {
                viewModel.getAccountBookDayTransaction(
                    calendarState.firstVisibleMonth.yearMonth.year,
                    calendarState.firstVisibleMonth.yearMonth.monthValue,
                    selectedDate.value.split("월 ")[1].replace("일", "").toInt()
                )
            },
            onShowToast = {
                isToastAnimating = true
                toastMessage = it
            }
        )
    }


    /**
     * Pull-To-Refresh
     */

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            viewModel.initializeBaseInfo(
                id = mainUiState.id,
                currentAccountInfo = mainUiState.currentAccountInfo,
                member = mainUiState.member
            )
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = White
                    ),
                    title = {
                        Text(
                            text = "가계부",
                            color = Grey700,
                            style = UligaTheme.typography.title2,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }
                )
            }

            item {

                VerticalSpacer(height = 8.dp)

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        ),
                    text = "${calendarState.firstVisibleMonth.yearMonth.monthValue}월",
                    color = Grey700,
                    style = UligaTheme.typography.title1
                )

                HorizontalCalendar(
                    modifier = Modifier
                        .padding(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        ),
                    state = calendarState,
                    dayContent = {
                        Day(
                            day = it,
                            accountBookAssetMonth = state.currentAccountBookAsset,
                            onClick = {
                                selectedDate.value = "${it.date.monthValue}월 ${it.date.dayOfMonth}일"
                                viewModel.getAccountBookDayTransaction(
                                    it.date.year,
                                    it.date.monthValue,
                                    it.date.dayOfMonth
                                )
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
                        text = selectedDate.value,
                        color = Grey700,
                        style = UligaTheme.typography.title1
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AddingButton(
                        text = "가계부에 추가하기",
                        onClick = {
                            val intent =
                                Intent(context, AccountBookForInputActivity::class.java)
                            intent.putExtra("selectedDate", selectedDate.value)
                            launcher.launch(intent)
                        }
                    )
                }
            }

            items(state.currentAccountBookAssetDay?.items?.size ?: 0) { idx ->

                val currentAccountBookAssetDay =
                    state.currentAccountBookAssetDay?.items?.get(idx) ?: return@items

                TransactionItem(
                    currentAccountBookAssetDay = currentAccountBookAssetDay,
                    onDeleteRequest = {
                        deleteAlertDialogVisibleState = true
                        selectedDeleteItemId = it.id
                    }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState
        )

    }

    if (state.isLoading) {
        CircularProgress()
    }

    TopDownToast(toastYOffset = toastYOffset, toastMessage = toastMessage)
}


@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: FinanceSideEffect,
    context: Context,
    onDismissDeleteAlert: () -> Unit,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is FinanceSideEffect.DismissDeleteAlert -> {
            onDismissDeleteAlert()
        }

        is FinanceSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }
    }

}
//@RequiresApi(Build.VERSION_CODES.Q)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun showSettingDropDownMenu(
//    showDialog: Boolean,
//) {
//    val scheduleSheetState = androidx.compose.material3.rememberModalBottomSheetState()
//    var isScheduleSheetStateOpen by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    if (isScheduleSheetStateOpen) {
////        ScheduleBottomSheet(
////            sheetState = scheduleSheetState,
////            onDismissRequest = {
////                isScheduleSheetStateOpen = false
////            }
////        )
//    }
//
//    var expanded by remember { mutableStateOf(true) }
//    val items = listOf(
//        "금융 일정 추가하기", "가계부 작성하기"
//    )
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentSize(Alignment.TopEnd)
//            .absolutePadding(top = 45.dp, right = 20.dp)
//    ) {
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .wrapContentWidth()
//                .background(
//                    Color.White
//                )
//        ) {
//            items.forEachIndexed { index, s ->
//                DropdownMenuItem(onClick = {
//                    expanded = false
////                    showDialog = false
//
//                    when (index) {
//                        0 -> {
//                            isScheduleSheetStateOpen = true
//                        }
//                    }
//
//                }) {
//                    Text(text = s)
//                    if (index < 3) {
//                        Spacer(modifier = Modifier.height(4.dp))
//                    }
//                }
//            }
//        }
//
//
//    }
//}