package com.uliga.app.view.home

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uliga.app.R
import com.uliga.app.ext.CircularProgress
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.Grey100
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.utils.Constant
import com.uliga.app.utils.TestTags
import com.uliga.app.view.accountBook.selection.AccountBookSelectionActivity
import com.uliga.app.view.budget.BudgetSettingBottomSheet
import com.uliga.app.view.component.AddingButton
import com.uliga.app.view.component.HorizontalLineIndicator
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.OneThicknessDivider
import com.uliga.app.view.component.TextWithDotImage
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.component.dialog.DeleteAlertDialog
import com.uliga.app.view.component.item.FinanceScheduleItem
import com.uliga.app.view.component.toast.TOAST_DURATION_MILLIS
import com.uliga.app.view.component.toast.TOAST_END_POSITION
import com.uliga.app.view.component.toast.TOAST_START_POSITION
import com.uliga.app.view.component.toast.TopDownToast
import com.uliga.app.view.home.invitation.InvitationBottomSheet
import com.uliga.app.view.main.MainActivity
import com.uliga.app.view.schedule.ScheduleGenerationActivity
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    val currentDate = LocalDate.now()
    val currentDay = currentDate.dayOfMonth

    val currentMonthAsset = state.currentMonthAccountBookAsset
    val currentMonthBudget = currentMonthAsset?.budget?.value ?: 0L
    val currentMonthRecord = currentMonthAsset?.record?.value ?: 0L
    val currentMonthResult = currentMonthBudget - currentMonthRecord


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            when (it.resultCode) {
                ComponentActivity.RESULT_OK -> {
                    viewModel.apply {
                        getFinanceSchedule()
                        getAccountBookFixedRecordByMonth()
                    }
                }
            }
        }
    )

    val budgetSettingBottomSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isBudgetSettingBottomSheetOpen by remember {
        mutableStateOf(false)
    }

    val invitationListBottomSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isInvitationListBottomSheetSheetOpen by remember {
        mutableStateOf(false)
    }

    var deleteAlertDialogVisibleState by remember {
        mutableStateOf(false)
    }

    val recordValue = state.currentMonthAccountBookAsset?.record?.value?.toFloat() ?: 0f

    var budgetValue = state.currentMonthAccountBookAsset?.budget?.value?.toFloat()

    if (budgetValue == null || budgetValue == 0f) budgetValue = 1f

    val animateNumber = animateFloatAsState(
        targetValue = if (recordValue >= budgetValue) 1f else recordValue / budgetValue,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0
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
        targetValue = if (isToastAnimating) TOAST_END_POSITION else TOAST_START_POSITION,
        animationSpec = tween(durationMillis = TOAST_DURATION_MILLIS),
        finishedListener = { endValue ->
            if (endValue == TOAST_END_POSITION) {
                isToastAnimating = false
            }
        },
        label = "",
    )

    /**
     * SideEffect
     */

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            sideEffect = sideEffect,
            context = context,
            onFinishBudgetSettingBottomSheet = {
                isBudgetSettingBottomSheetOpen = false
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
            viewModel.initialize()
        }
    )

    BackHandler {
        if (isInvitationListBottomSheetSheetOpen) {
            isInvitationListBottomSheetSheetOpen = false
        } else if (isBudgetSettingBottomSheetOpen) {
            isBudgetSettingBottomSheetOpen = false
        } else {
            (context as MainActivity).finish()
        }
    }


    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                                .size(28.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        color = Color.Black
                                    ),
                                    onClick = {
                                        isInvitationListBottomSheetSheetOpen = true
                                    }
                                )
                                .testTag(TestTags.INVITATION),
                            painter = painterResource(
                                id = R.drawable.ic_notification
                            ),
                            contentDescription = null
                        )
                    }
                }
            )


        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize()
                .background(White)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.TopCenter
        ) {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {

                    VerticalSpacer(height = 16.dp)

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
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
                                .border(
                                    width = 1.dp,
                                    color = Grey100,
                                    shape = UligaTheme.shapes.medium
                                )
                                .fillMaxWidth()
                                .padding(
                                    vertical = 16.dp,
                                    horizontal = 8.dp
                                )
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = state.accountBookName ?: "",
                                color = Grey600,
                                style = UligaTheme.typography.body3
                            )

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            color = Color.Black
                                        ),
                                        onClick = {
                                            val intent = Intent(
                                                context,
                                                AccountBookSelectionActivity::class.java
                                            )
                                            intent.putExtra("from", "home")
                                            context.startActivity(intent)
                                        }
                                    )
                            )
                        }
                    }
                }

                item {

                    VerticalSpacer(height = 32.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
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
                                isBudgetSettingBottomSheetOpen = true
                            },
                            testTag = TestTags.BUDGET_SETTING
                        )
                    }

                    VerticalSpacer(height = 8.dp)

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        text = if (currentMonthResult >= 0) "${currentMonthResult}원 남음" else "${currentMonthResult * (-1)}원 부족",
                        color = Grey600,
                        style = UligaTheme.typography.body3
                    )

                    VerticalSpacer(height = 24.dp)

                    HorizontalLineIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Constant.indicatorHeight)
                            .padding(horizontal = Constant.indicatorPadding + 20.dp),
                        animateNumber = animateNumber.value
                    )

                    VerticalSpacer(height = 8.dp)

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                    )

                    VerticalSpacer(height = 12.dp)

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
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
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
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
                }

                item {

                    VerticalSpacer(height = 32.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
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
                                val intent = Intent(context, ScheduleGenerationActivity::class.java)
                                launcher.launch(intent)
                            },
                            testTag = TestTags.ADDING_FINANCE_SCHEDULE
                        )
                    }

                }

                val totalSize = state.financeSchedules?.schedules?.size ?: 0

                if (totalSize == 0) {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .height(210.dp)
                                .border(
                                    width = 1.dp,
                                    color = Grey100,
                                    shape = UligaTheme.shapes.medium
                                )
                                .padding(horizontal = 4.dp)
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
                        if (idx <= 2) {
                            FinanceScheduleItem(
                                state = state,
                                idx = idx,
                                currentDay = currentDay,
                                onFinanceScheduleUpdateRequest = { financeSchedule ->
                                    val intent =
                                        Intent(context, ScheduleGenerationActivity::class.java)
                                    intent.putExtra(
                                        "notificationDay",
                                        financeSchedule.notificationDay
                                    )
                                    intent.putExtra("isIncome", financeSchedule.isIncome)
                                    intent.putExtra("name", financeSchedule.name)
                                    intent.putExtra("value", financeSchedule.value)
                                    intent.putExtra("scheduleId", financeSchedule.id)
                                    launcher.launch(intent)
                                },
                                onFinanceScheduleDeleteRequest = { financeSchedule ->
                                    viewModel.updateFinanceSchedule(financeSchedule)
                                }
                            )
                        }

                    }
                }

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .background(
                                color = CustomGrey100,
                                shape = UligaTheme.shapes.medium
                            )
                            .padding(4.dp),
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

                    VerticalSpacer(height = 32.dp)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = "나의 고정 지출",
                        color = Grey700,
                        style = UligaTheme.typography.title3
                    )
                }

                val fixedRecordList =
                    state.accountBookAnalyzeFixedRecordByMonth?.schedules

                if (fixedRecordList.isNullOrEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(
                                    width = 1.dp,
                                    color = Grey100,
                                    shape = UligaTheme.shapes.medium
                                )
                                .padding(horizontal = 4.dp)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "고정 지출 정보가 존재하지 않습니다.",
                                color = Grey600,
                                style = UligaTheme.typography.body5,
                            )
                        }
                    }
                } else {
                    items(fixedRecordList.size) { idx ->

                        Row(
                            modifier = Modifier.padding(horizontal = 20.dp)
                        ) {
                            Text(
                                text = "${fixedRecordList[idx].day}일",
                                color = Grey500,
                                style = UligaTheme.typography.body12
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = fixedRecordList[idx].name,
                                color = Grey500,
                                style = UligaTheme.typography.body12
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "${fixedRecordList[idx].value}원",
                                color = Grey500,
                                style = UligaTheme.typography.body12
                            )
                        }

                    }
                }

                item {
                    VerticalSpacer(height = 16.dp)
                }
            }

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState
            )
        }
    }

    if (state.selectedSchedule != null) {
        DeleteAlertDialog(
            onDismissRequest = {
                deleteAlertDialogVisibleState = false
            },
            onDeleteRequest = {
                viewModel.deleteFinanceScheduleDetail(state.selectedSchedule.id)
                deleteAlertDialogVisibleState = false
                viewModel.updateFinanceSchedule(null)
            },
            title = "금융 일정 삭제",
            subTitle = "정말 선택한 금융 일정을 삭제하시겠어요?"
        )
    }

    if (isBudgetSettingBottomSheetOpen) {
        BudgetSettingBottomSheet(
            sheetState = budgetSettingBottomSheetState,
            viewModel = viewModel,
            currentDate = currentDate,
            onDismissRequest = {
                viewModel.updateFinanceSchedule(null)
                isBudgetSettingBottomSheetOpen = false
            }
        )
    }

    if (isInvitationListBottomSheetSheetOpen) {
        InvitationBottomSheet(
            sheetState = invitationListBottomSheetState,
            viewModel = viewModel,
            onDismissRequest = {
                isInvitationListBottomSheetSheetOpen = false
            }
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
    onFinishBudgetSettingBottomSheet: () -> Unit,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is HomeSideEffect.FinishBudgetSettingBottomSheet -> {
            onFinishBudgetSettingBottomSheet()
        }

        is HomeSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }
    }
}