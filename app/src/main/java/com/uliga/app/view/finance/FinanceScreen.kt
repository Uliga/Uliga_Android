package com.uliga.app.view.finance

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.uliga.app.view.component.toast.TopDownToast
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ext.CircularProgress
import com.uliga.app.view.component.dialog.DeleteAlertDialog
import com.uliga.app.view.accountBook.input.AccountBookInputActivity
import com.uliga.app.view.component.AddingButton
import com.uliga.app.view.component.Day
import com.uliga.app.view.component.DaysOfWeekTitle
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.component.item.TransactionItem
import com.uliga.app.view.main.MainUiState
import kotlinx.coroutines.runBlocking
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate
import java.time.YearMonth

@SuppressLint("NewApi", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun FinanceScreen(
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

    var selectedDateState by
    remember { mutableStateOf("${currentDate.year}년 ${currentDate.monthValue}월 ${currentDate.dayOfMonth}일") }

    LaunchedEffect(key1 = calendarState.firstVisibleMonth) {
        viewModel.getAccountBookMonthTransaction(
            calendarState.firstVisibleMonth.yearMonth.year,
            calendarState.firstVisibleMonth.yearMonth.monthValue
        )

    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
//                    viewModel.getAccountBookMonthTransaction(
//                        calendarState.firstVisibleMonth.yearMonth.year,
//                        calendarState.firstVisibleMonth.yearMonth.monthValue
//                    )
//                    viewModel.getAccountBookDayTransaction(
//                        calendarState.firstVisibleMonth.yearMonth.year,
//                        calendarState.firstVisibleMonth.yearMonth.monthValue,
//                        selectedDateState.split("월 ")[1].replace("일", "").toInt()
//                    )

                    viewModel.getAccountBookTransaction(
                        calendarState.firstVisibleMonth.yearMonth.year,
                        calendarState.firstVisibleMonth.yearMonth.monthValue,
                        selectedDateState.split("월 ")[1].replace("일", "").toInt()
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
            onDismissDeleteAlert = {
                viewModel.getAccountBookTransaction(
                    calendarState.firstVisibleMonth.yearMonth.year,
                    calendarState.firstVisibleMonth.yearMonth.monthValue,
                    selectedDateState.split("월 ")[1].replace("일", "").toInt()
                )

                deleteAlertDialogVisibleState = false
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
        Scaffold(
            topBar = {
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
                    },
                    modifier = Modifier.shadow(5.dp)
                )
            }
        ) { paddingValue ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValue)
                    .fillMaxSize()
                    .background(White),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                item {

                    VerticalSpacer(height = 32.dp)

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
                        dayContent = { calendarDay ->
                            Day(
                                day = calendarDay,
                                accountBookAssetMonth = state.currentAccountBookAsset,
                                onClick = {
                                    selectedDateState =
                                        "${it.date.monthValue}월 ${it.date.dayOfMonth}일"
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
                            text = selectedDateState,
                            color = Grey700,
                            style = UligaTheme.typography.title1
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        AddingButton(
                            text = "가계부에 추가하기",
                            onClick = {
                                val intent =
                                    Intent(context, AccountBookInputActivity::class.java)
                                intent.putExtra("selectedDate", selectedDateState)
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
        }


        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState
        )

    }

    if (deleteAlertDialogVisibleState) {
        DeleteAlertDialog(
            onDismissRequest = {
                deleteAlertDialogVisibleState = false
            },
            onDeleteRequest = {
                runBlocking {
                    viewModel.deleteAccountBookDayTransaction(selectedDeleteItemId)

                }

                viewModel.getAccountBookMonthTransaction(
                    calendarState.firstVisibleMonth.yearMonth.year,
                    calendarState.firstVisibleMonth.yearMonth.monthValue
                )
            },
            title = "거래 내역 삭제",
            subTitle = "정말 선택한 거래 내역을 삭제하시겠어요?"
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