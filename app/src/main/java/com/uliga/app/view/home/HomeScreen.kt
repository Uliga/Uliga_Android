package com.uliga.app.view.home

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uliga.app.R
import com.uliga.app.TopDownToast
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.Grey100
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.DeleteAlertDialog
import com.uliga.app.view.budget.BudgetSettingBottomSheet
import com.uliga.app.view.component.AddingButton
import com.uliga.app.view.component.ContinuousLineChart
import com.uliga.app.view.component.FinanceScheduleItem
import com.uliga.app.view.component.HorizontalLineIndicator
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.OneThicknessDivider
import com.uliga.app.view.component.TextWithDotImage
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.home.invitation.InvitationBottomSheet
import com.uliga.app.view.main.MainUiState
import com.uliga.app.view.schedule.ScheduleAlertBottomSheet
import com.uliga.app.view.schedule.ScheduleBottomSheet
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(
    navController: NavController,
    mainUiState: MainUiState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    viewModel.initializeBaseInfo(
        id = mainUiState.id,
        currentAccountInfo = mainUiState.currentAccountInfo,
        member = mainUiState.member
    )

    /**
     * Need Refactor
     */

    val currentDate = LocalDate.now()

    val currentDay = currentDate.dayOfMonth

    val currentMonthAsset = state.currentMonthAccountBookAsset
    val currentMonthBudget = currentMonthAsset?.budget?.value ?: 0L
    val currentMonthRecord = currentMonthAsset?.record?.value ?: 0L
    val currentMonthResult = currentMonthBudget - currentMonthRecord

    val budgetSettingSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isBudgetSettingSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isBudgetSettingSheetOpen) {
        BudgetSettingBottomSheet(
            sheetState = budgetSettingSheetState,
            viewModel = viewModel,
            currentDate = currentDate,
            onDismissRequest = {
                viewModel.updateFinanceSchedule(null)
                isBudgetSettingSheetOpen = false
            }
        )
    }

    val invitationListSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isInvitationListSheetSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isInvitationListSheetSheetOpen) {
        InvitationBottomSheet(
            sheetState = invitationListSheetState,
            viewModel = viewModel,
            onDismissRequest = {
                isInvitationListSheetSheetOpen = false
            }
        )
    }

    val scheduleAlertSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isScheduleAlertSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    var deleteAlertDialogVisibleState by remember {
        mutableStateOf(false)
    }

    if (isScheduleAlertSheetOpen) {
        ScheduleAlertBottomSheet(
            sheetState = scheduleAlertSheetState,
            onDismissRequest = {
                isScheduleAlertSheetOpen = false
            }
        )
    }

    val scheduleSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isScheduleSheetStateOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isScheduleSheetStateOpen) {
        ScheduleBottomSheet(
            sheetState = scheduleSheetState,
            viewModel = viewModel,
            onDismissRequest = {
                isScheduleSheetStateOpen = false
            }
        )
    }

    var recordValue = state.currentMonthAccountBookAsset?.record?.value?.toFloat()
    if (recordValue == null) recordValue = 0f
    var budgetValue = state.currentMonthAccountBookAsset?.budget?.value?.toFloat()
    if (budgetValue == null || budgetValue == 0f) budgetValue = 1f

    val animationDuration: Int = 1000
    val animationDelay: Int = 0
    val animateNumber = animateFloatAsState(
        targetValue = recordValue / budgetValue,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ), label = ""
    )

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

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            sideEffect = sideEffect,
            context = context,
            onFinishScheduleBottomSheet = {
                isScheduleAlertSheetOpen = false
            },
            onFinishBudgetSettingBottomSheet = {
                isBudgetSettingSheetOpen = false
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
            viewModel.apply {
                initializeBaseInfo(
                    id = mainUiState.id,
                    currentAccountInfo = mainUiState.currentAccountInfo,
                    member = mainUiState.member
                )
                initialize()
            }
        }
    )

    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(White)
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.TopCenter
    ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {

                Row {
                    Image(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(
                            id = R.drawable.uliga_logo
                        ),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(
                            end = 12.dp
                        ),
                        text = "우리가",
                        color = Primary,
                        style = UligaTheme.typography.title1
                    )

                    Spacer(
                        Modifier.weight(1f)
                    )

                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                isInvitationListSheetSheetOpen = true
                            },
                        painter = painterResource(
                            id = R.drawable.uliga_logo
                        ),
                        contentDescription = null
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "현재 가계부",
                        color = Grey700,
                        style = UligaTheme.typography.title3
                    )

                    VerticalSpacer(height = 8.dp)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp
                            )
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = state.currentAccountInfo?.first ?: "",
                            color = Grey600,
                            style = UligaTheme.typography.body3
                        )

                        Text(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = "변경 버튼",
                            color = Grey600,
                            style = UligaTheme.typography.body3
                        )
                    }

                    Row {

                    }

                    VerticalSpacer(height = 16.dp)

                }
            }

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "이번 달 예산",
                        color = Grey700,
                        style = UligaTheme.typography.title3
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AddingButton(
                        text = "예산 설정하러 가기",
                        onClick = {
                            isBudgetSettingSheetOpen = true
                        }
                    )
                }

                VerticalSpacer(height = 8.dp)

                Text(
                    text = if (currentMonthResult >= 0) "${currentMonthResult}원 남음" else "${currentMonthResult * (-1)}원 부족",
                    color = Grey600,
                    style = UligaTheme.typography.body3
                )

                VerticalSpacer(height = 24.dp)

                HorizontalLineIndicator(
                    animateNumber = animateNumber.value
                )

                VerticalSpacer(height = 8.dp)

                OneThicknessDivider(
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                    )
                )

                VerticalSpacer(height = 12.dp)

                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextWithDotImage(
                        text = "${currentDate.monthValue}월 설정 예산",
                        painter = painterResource(
                            id = R.drawable.ic_gray_dot
                        ),
                        imageSize = 12.dp,
                        textStyle = UligaTheme.typography.body4,
                        textColor = Grey700
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${currentMonthBudget}원",
                        color = Grey700,
                        style = UligaTheme.typography.body4
                    )
                }

                VerticalSpacer(height = 4.dp)

                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextWithDotImage(
                        text = "남은 1일 예산",
                        painter = painterResource(
                            id = R.drawable.ic_gray_dot
                        ),
                        imageSize = 12.dp,
                        textStyle = UligaTheme.typography.body4,
                        textColor = Grey700
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${currentMonthBudget / currentDate.dayOfMonth}원",
                        color = Grey700,
                        style = UligaTheme.typography.body4
                    )
                }

                VerticalSpacer(height = 16.dp)
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "다가오는 금융 일정",
                        color = Grey700,
                        style = UligaTheme.typography.title3
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AddingButton(
                        text = "금융 일정 추가하기",
                        onClick = {
                            isScheduleSheetStateOpen = true
                        }
                    )
                }

            }

            val totalSize = state.financeSchedules?.schedules?.size ?: 0

            if (totalSize == 0) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .border(
                                width = 1.dp,
                                color = Grey100,
                                shape = UligaTheme.shapes.medium
                            )
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "금융 일정이 존재하지 않습니다.",
                            color = Grey600,
                            style = UligaTheme.typography.body5,
                        )
                    }
                }
            } else {
                items(totalSize) { idx ->
                    FinanceScheduleItem(
                        state = state,
                        idx = idx,
                        currentDay = currentDay,
                        onFinanceScheduleUpdateRequest = { financeSchedule ->
                            viewModel.updateFinanceSchedule(financeSchedule)
                            isScheduleSheetStateOpen = true
                        },
                        onFinanceScheduleDeleteRequest = { financeSchedule ->
                            viewModel.updateFinanceSchedule(financeSchedule)
                            deleteAlertDialogVisibleState = true
                        }
                    )

                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = CustomGrey100,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    VerticalSpacer(height = 8.dp)

                    Row {
                        TextWithDotImage(
                            text = "약 3일 미만의 기간이 남음",
                            painter = painterResource(
                                id = R.drawable.ic_red_dot
                            ),
                            imageSize = 8.dp,
                            textStyle = UligaTheme.typography.body5,
                            textColor = Grey600
                        )

                        HorizontalSpacer(width = 16.dp)
                    }

                    Row {
                        TextWithDotImage(
                            text = "일주일 미만의 기간이 남음",
                            painter = painterResource(
                                id = R.drawable.ic_orange_dot
                            ),
                            imageSize = 10.dp,
                            textStyle = UligaTheme.typography.body5,
                            textColor = Grey600
                        )

                        HorizontalSpacer(width = 16.dp)
                    }

                    Row {
                        TextWithDotImage(
                            text = "일주일 이상의 기간이 남음",
                            painter = painterResource(
                                id = R.drawable.ic_green_dot
                            ),
                            imageSize = 8.dp,
                            textStyle = UligaTheme.typography.body5,
                            textColor = Grey600
                        )

                        HorizontalSpacer(width = 16.dp)
                    }

                    VerticalSpacer(height = 8.dp)

                }
            }

            item {

                VerticalSpacer(height = 8.dp)

                Text(
                    text = "이번 달 총 지출",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )

                VerticalSpacer(height = 8.dp)

                Text(
                    text = "${currentMonthRecord}원",
                    color = Grey600,
                    style = UligaTheme.typography.body3
                )

                ContinuousLineChart(
                    accountBookAnalyzeRecordByDay = state.accountBookAnalyzeRecordByDay
                )
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
                viewModel.deleteFinanceScheduleDetail(state.selectedSchedule?.id ?: 0L)
                deleteAlertDialogVisibleState = false
                viewModel.updateFinanceSchedule(null)
            },
            title = "금융 일정 삭제",
            subTitle = "정말 선택한 금융 일정을 삭제하시겠어요?"
        )
    }

    if (state.isLoading) {
        CircularProgress()
    }

    TopDownToast(toastYOffset = toastYOffset, toastMessage = toastMessage)
}


@RequiresApi(Build.VERSION_CODES.Q)

private fun handleSideEffect(
    sideEffect: HomeSideEffect,
    context: Context,
    onFinishScheduleBottomSheet: () -> Unit,
    onFinishBudgetSettingBottomSheet: () -> Unit,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is HomeSideEffect.FinishScheduleBottomSheet -> {
            onFinishScheduleBottomSheet()
        }

        is HomeSideEffect.FinishBudgetSettingBottomSheet -> {
            onFinishBudgetSettingBottomSheet()
        }

        is HomeSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }
    }
}