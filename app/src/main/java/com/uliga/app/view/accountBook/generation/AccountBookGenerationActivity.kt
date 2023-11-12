package com.uliga.app.view.accountBook.generation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.TOAST_DURATION_MILLIS
import com.uliga.app.TOAST_END_POSITION
import com.uliga.app.TOAST_START_POSITION
import com.uliga.app.TopDownToast
import com.uliga.app.ui.theme.CustomGrey200
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.component.BasicTextField
import com.uliga.app.view.component.CheckButton
import com.uliga.app.view.component.Chips
import com.uliga.app.view.component.ChipsWithDelete
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class AccountBookGenerationActivity : ComponentActivity() {

    private val viewModel: AccountBookGenerationViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UligaApplicationTheme {

                val context = LocalContext.current
                val state = viewModel.collectAsState().value

                var accountBookNameTextState by remember {
                    mutableStateOf("")
                }

                var relationshipTextState by remember {
                    mutableStateOf("")
                }


                var accountBookInvitationTextState by remember {
                    mutableStateOf("")
                }

                val invitationElementListState = remember {
                    mutableStateListOf("")
                }

                var accountBookCategoryTextState by remember {
                    mutableStateOf("")
                }

                val categoryElementListState = remember {
                    mutableStateListOf("식비", "식비", "카페 간식", "생활", "편의점,마트, 잡화")
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

                viewModel.collectSideEffect {
                    handleSideEffect(
                        sideEffect = it,
                        context = context,
                        onEmailAddingRequest = {
                            invitationElementListState.add(accountBookInvitationTextState)
                        },
                        onFinishRequest = {
                            setResult(RESULT_OK)
                            finish()
                        },
                        onShowToast = {
                            isToastAnimating = true
                            toastMessage = it
                        }
                    )
                }

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = White
                            ),
                            title = {
                                Text(
                                    text = "가계부 만들기",
                                    color = Grey700,
                                    style = UligaTheme.typography.title2,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                )
                            },
                            modifier = Modifier.shadow(5.dp)
                        )
                    },
                    bottomBar = {
                        PositiveButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = "공유 가계부 만들기",
                            contentPadding = PaddingValues(
                                vertical = 16.dp
                            ),
                            onClick = {
                                viewModel.postAccountBook(
                                    name = accountBookNameTextState,
                                    relationship = relationshipTextState,
                                    categoryList = categoryElementListState.toList(),
                                    emailList = invitationElementListState.toList()
                                )
                            }
                        )
                    }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .wrapContentSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(Color.White),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {

                            item {

                                VerticalSpacer(height = 32.dp)

                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "가계부 이름",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    BasicTextField(
                                        value = accountBookNameTextState,
                                        onValueChange = {
                                            accountBookNameTextState = it
                                        },
                                        keyboardType = KeyboardType.Text,
                                        hint = "가계부 이름을 입력해주세요."
                                    )
                                }
                            }

                            item {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "관계",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    BasicTextField(
                                        value = relationshipTextState,
                                        onValueChange = {
                                            relationshipTextState = it
                                        },
                                        keyboardType = KeyboardType.Text,
                                        hint = "공유 가계부 조직의 관계를 입력해주세요."
                                    )
                                }
                            }

                            item {
                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "사용자 초대",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier.weight(3.5f)
                                    ) {
                                        BasicTextField(
                                            value = accountBookInvitationTextState,
                                            onValueChange = {
                                                accountBookInvitationTextState = it
                                            },
                                            keyboardType = KeyboardType.Text,
                                            hint = "사용자의 이메일을 입력해주세요."
                                        )
                                    }

                                    CheckButton(
                                        enabled = true,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                            .padding(horizontal = 4.dp),
                                        contentPadding = PaddingValues(
                                            vertical = 4.dp
                                        ),
                                        textColor = Primary,
                                        backgroundColor = ButtonDefaults.buttonColors(
                                            backgroundColor = CustomGrey200
                                        ),
                                        text = "초대",
                                        onClick = {
                                            viewModel.getIsEmailExisted(
                                                accountBookInvitationTextState
                                            )
                                        }
                                    )
                                }

                                ChipsWithDelete(
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp),
                                    elements = invitationElementListState.apply {
                                        remove("")
                                    },
                                    onDeleteButtonClicked = { content ->
                                        invitationElementListState.remove(content)
                                    }
                                )

                            }

                            item {

                                Text(
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    text = "카테고리 추가",
                                    color = Grey600,
                                    style = UligaTheme.typography.body3
                                )

                                VerticalSpacer(height = 8.dp)

                                Row(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Row(
                                        modifier = Modifier.weight(3.5f)
                                    ) {
                                        BasicTextField(
                                            value = accountBookCategoryTextState,
                                            onValueChange = {
                                                accountBookCategoryTextState = it
                                            },
                                            keyboardType = KeyboardType.Text,
                                            hint = "추가할 카테고리명을 입력해주세요."
                                        )
                                    }

                                    CheckButton(
                                        enabled = true,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                            .padding(horizontal = 4.dp),
                                        contentPadding = PaddingValues(
                                            vertical = 4.dp
                                        ),
                                        textColor = Primary,
                                        backgroundColor = ButtonDefaults.buttonColors(
                                            backgroundColor = CustomGrey200
                                        ),
                                        text = "추가",
                                        onClick = {
                                            categoryElementListState.add(
                                                accountBookCategoryTextState
                                            )
                                        }
                                    )
                                }

                                Chips(
                                    modifier = Modifier.padding(horizontal = 20.dp),
                                    elements = categoryElementListState
                                )
                            }
                        }
                    }
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
    sideEffect: AccountBookGenerationSideEffect,
    context: Context,
    onFinishRequest: () -> Unit,
    onEmailAddingRequest: () -> Unit,
    onShowToast: (String) -> Unit,
) {
    when (sideEffect) {
        is AccountBookGenerationSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

        is AccountBookGenerationSideEffect.Finish -> {
            onFinishRequest()
        }

        is AccountBookGenerationSideEffect.AddEmailChip -> {
            onEmailAddingRequest()
        }

        else -> { // no-op
        }
    }
}