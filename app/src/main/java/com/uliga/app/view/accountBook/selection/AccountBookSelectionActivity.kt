package com.uliga.app.view.accountBook.selection

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.TopDownToast
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.accountBook.generation.AccountBookGenerationActivity
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.component.item.AccountBookItem
import com.uliga.app.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@AndroidEntryPoint
class AccountBookSelectionActivity : ComponentActivity() {

    private val viewModel: AccountBookSelectionViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @SuppressLint("UnrememberedMutableState")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UligaApplicationTheme {

                val context = LocalContext.current
                val state = viewModel.collectAsState().value

                var selectedIndex by remember { mutableStateOf(-1) }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {
                        when (it.resultCode) {
                            RESULT_OK -> {
                                viewModel.getAccountBooks()
                            }
                        }
                    }
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
                        context = this,
                        onShowToast = {
                            isToastAnimating = true
                            toastMessage = it
                        },
                    )
                }

                /**
                 * Pull-To-Refresh
                 */

                val pullRefreshState = rememberPullRefreshState(
                    refreshing = state.isLoading,
                    onRefresh = {
                        viewModel.getAccountBooks()
                    }
                )

                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .pullRefresh(pullRefreshState),
                    contentAlignment = Alignment.TopCenter
                ) {
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        item {

                            CenterAlignedTopAppBar(
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = White
                                ),
                                title = {
                                    Row {
                                        Text(
                                            text = "가계부 선택하기",
                                            color = Grey700,
                                            style = UligaTheme.typography.title2,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1,
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(
                                        onClick = {
                                            val intent = Intent(
                                                context,
                                                AccountBookGenerationActivity::class.java
                                            )
                                            launcher.launch(intent)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )

                        }

                        item {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp
                                ),
                                text = "가계부 목록",
                                color = Grey700,
                                style = UligaTheme.typography.title1,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            VerticalSpacer(height = 16.dp)

                        }

                        items(state.accountBooks?.accountBooks?.size ?: 0) {

                            val accountBookName =
                                state.accountBooks?.accountBooks?.get(it)?.info?.accountBookName
                                    ?: ""

                            AccountBookItem(
                                accountBookName = accountBookName,
                                accountBookColor = if (selectedIndex == it) Primary else Grey500,
                                accountBookImageAlpha = if (selectedIndex == it) 1f else 0f,
                                onClick = { selectedIndex = it }
                            )
                        }

                        item {

                            PositiveButton(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                text = "우리가 시작하기",
                                contentPadding = PaddingValues(
                                    vertical = 16.dp
                                ),
                                onClick = {
                                    viewModel.updateAccountBook(selectedIndex, state.accountBooks)
                                }
                            )
                        }
                    }

                    Button(onClick = { viewModel.deleteMember() }) {

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
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: AccountBookSelectionSideEffect,
    context: Context,
    onShowToast: (String) -> Unit,
) {
    when (sideEffect) {
        is AccountBookSelectionSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

        is AccountBookSelectionSideEffect.NavigateToMainActivity -> {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        else -> { // no-op
        }
    }
}