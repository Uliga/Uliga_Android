package com.uliga.app.view.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uliga.app.BuildConfig
import com.uliga.app.R
import com.uliga.app.TOAST_DURATION_MILLIS
import com.uliga.app.TOAST_END_POSITION
import com.uliga.app.TOAST_START_POSITION
import com.uliga.app.TopDownToast
import com.uliga.app.ui.theme.Grey100
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.DeleteAlertDialog
import com.uliga.app.view.auth.AuthActivity
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.OneThicknessDivider
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.main.MainActivity
import com.uliga.app.view.main.MainUiState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun ProfileScreen(
    mainUiState: MainUiState,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    /**
     * Initialize
     */

    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    viewModel.initializeBaseInfo(
        id = mainUiState.id,
        currentAccountInfo = mainUiState.currentAccountInfo,
        member = mainUiState.member
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
     * Logout / SignOut Alert Dialog
     */

    var logoutAlertDialogVisibleState by remember {
        mutableStateOf(false)
    }

    var signOutAlertDialogVisibleState by remember {
        mutableStateOf(false)
    }

    /**
     * SideEffect
     */

    viewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            sideEffect = sideEffect,
            context = context,
            onShowLogoutAlertDialog = {
                logoutAlertDialogVisibleState = false
            },
            onShowSignOutAlertDialog = {
                signOutAlertDialogVisibleState = false
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                ),
                title = {
                    Text(
                        text = "마이페이지",
                        color = Grey700,
                        style = UligaTheme.typography.title2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                modifier = Modifier.shadow(5.dp)
            )
        }
    ) { paddingValues ->


        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
                .pullRefresh(pullRefreshState)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {

                    VerticalSpacer(height = 32.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp)
                            .border(
                                width = 1.dp,
                                color = Grey100,
                                shape = UligaTheme.shapes.medium
                            )
                            .padding(
                                vertical = 12.dp,
                                horizontal = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp),
                            painter = painterResource(
                                id = R.drawable.ic_my
                            ),
                            contentDescription = null
                        )

                        HorizontalSpacer(width = 16.dp)

                        Column {
                            Text(
                                modifier = Modifier.padding(
                                    end = 12.dp
                                ),
                                text = state.member?.memberInfo?.userName ?: "",
                                color = Grey600,
                                style = UligaTheme.typography.body3,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            VerticalSpacer(height = 4.dp)

                            Text(
                                modifier = Modifier.padding(
                                    end = 12.dp
                                ),
                                text = state.member?.memberInfo?.email ?: "",
                                color = Grey600,
                                style = UligaTheme.typography.body3,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
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
                        color = Grey700,
                        style = UligaTheme.typography.title1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black
                                ),
                                onClick = {
                                }
                            )
                            .padding(4.dp),
                        text = "기본 정보",
                        color = Grey600,
                        style = UligaTheme.typography.body3,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black
                                ),
                                onClick = {
                                }
                            )
                            .padding(4.dp),
                        text = "가계부 정보",
                        color = Grey600,
                        style = UligaTheme.typography.body3,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                    Row {
                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(
                                    vertical = 8.dp,
                                    horizontal = 12.dp
                                )
                                .padding(4.dp),
                            text = "버전 정보",
                            color = Grey600,
                            style = UligaTheme.typography.body3,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(
                                    vertical = 8.dp,
                                    horizontal = 12.dp
                                )
                                .padding(4.dp),
                            text = BuildConfig.VERSION_NAME,
                            color = Grey600,
                            style = UligaTheme.typography.body3,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
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
                        color = Grey700,
                        style = UligaTheme.typography.title1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black
                                ),
                                onClick = {
                                    logoutAlertDialogVisibleState = true
                                }
                            )
                            .padding(4.dp),
                        text = "로그아웃",
                        color = Grey600,
                        style = UligaTheme.typography.body3,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp,
                                horizontal = 12.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black
                                ),
                                onClick = {
                                    signOutAlertDialogVisibleState = true
                                }
                            )
                            .padding(4.dp),

                        text = "탈퇴하기",
                        color = Grey600,
                        style = UligaTheme.typography.body3,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    OneThicknessDivider(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp
                            )
                    )

                }
            }

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState
            )
        }

    }

    if (logoutAlertDialogVisibleState) {
        DeleteAlertDialog(
            onDismissRequest = {
                logoutAlertDialogVisibleState = false
            },
            onDeleteRequest = {
                viewModel.getLogoutRedirect()
                logoutAlertDialogVisibleState = false
            },
            title = "정말로 로그아웃 하시겠습니까?",
            subTitle = ""
        )
    }

    if (signOutAlertDialogVisibleState) {
        DeleteAlertDialog(
            onDismissRequest = {
                signOutAlertDialogVisibleState = false
            },
            onDeleteRequest = {
                viewModel.deleteMember()
                signOutAlertDialogVisibleState = false
            },
            title = "정말로 회원탈퇴를 하시겠습니까?",
            subTitle = "지금까지 사용하셨던 정보들이 모두 삭제될 수 있어요."
        )
    }

    if (state.isLoading) {
        CircularProgress()
    }

    TopDownToast(toastYOffset = toastYOffset, toastMessage = toastMessage)
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: ProfileSideEffect,
    context: Context,
    onShowLogoutAlertDialog: () -> Unit,
    onShowSignOutAlertDialog: () -> Unit,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is ProfileSideEffect.DismissLogoutAlert -> {
            onShowLogoutAlertDialog()
        }

        is ProfileSideEffect.DismissSignOutAlert -> {
            onShowSignOutAlertDialog()
        }

        is ProfileSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

        is ProfileSideEffect.MoveToAuthActivity -> {
            val intent = Intent(context, AuthActivity::class.java)
            context.startActivity(intent)
        }

        is ProfileSideEffect.Finish -> {
            (context as MainActivity).finish()
        }
    }
}