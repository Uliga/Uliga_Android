package com.uliga.app.view.auth.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uliga.app.R
import com.uliga.app.ToastAnimation
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.CustomGrey200
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.accountBook.selection.AccountBookSelectionActivity
import com.uliga.app.view.auth.AuthSideEffect
import com.uliga.app.view.auth.AuthViewModel
import com.uliga.app.view.component.AgreeCheckbox
import com.uliga.app.view.component.BasicTextField
import com.uliga.app.view.component.CheckButton
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.main.MainActivity
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SocialSignupScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    paramEmail: String,
    paramName: String
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    val emailAddressTextState = remember { mutableStateOf(paramEmail) }
    val nameTextState = remember { mutableStateOf(paramName) }
    val nickNameTextState = remember { mutableStateOf("") }
    val privacyCheckBoxState = remember { mutableStateOf(state.isPrivacyChecked) }

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
            onShowToast = {
                isToastAnimating = true
                toastMessage = it
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        item {

            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                ),
                title = {
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
                    }
                },
            )

        }

        item {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    text = "회원가입",
                    color = Grey700,
                    style = UligaTheme.typography.title1
                )

                VerticalSpacer(height = 8.dp)

                Text(
                    text = "만나서 반가워요! 당신의 건강한 소비 생활을 응원합니다\uD83D\uDE4B\u200D♀️",
                    color = Grey500,
                    style = UligaTheme.typography.body12,
                    maxLines = 2
                )

                VerticalSpacer(height = 20.dp)
            }
        }

        item {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {

                Text(
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "닉네임",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )

                VerticalSpacer(height = 8.dp)

                Row {
                    Box(
                        modifier = Modifier.weight(4f)
                    ) {
                        BasicTextField(
                            value = nickNameTextState.value,
                            onValueChange = {
                                nickNameTextState.value = it
                            },
                            keyboardType = KeyboardType.Text
                        )
                    }

                    CheckButton(
                        enabled = state.isNickNameExisted == null || state.isNickNameExisted,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        contentPadding = PaddingValues(
                            vertical = 4.dp
                        ),
                        textColor = if (state.isNickNameExisted == null || state.isNickNameExisted) Primary else White,
                        backgroundColor = ButtonDefaults.buttonColors(
                            backgroundColor =
                            if (state.isNickNameExisted == null || state.isNickNameExisted) CustomGrey200
                            else CustomGrey100,
                        ),
                        text = "확인",
                        onClick = {
                            viewModel.getIsNickNameExisted(nickNameTextState.value)
                        }
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "이메일 주소",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )

                VerticalSpacer(height = 8.dp)

                BasicTextField(
                    value = emailAddressTextState.value,
                    onValueChange = {
                        emailAddressTextState.value = it
                    },
                    keyboardType = KeyboardType.Text
                )
            }
        }


        item {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "이름",
                    color = Grey700,
                    style = UligaTheme.typography.title3
                )

                VerticalSpacer(height = 8.dp)

                BasicTextField(
                    value = nameTextState.value,
                    onValueChange = {
                        nameTextState.value = it
                    },
                    keyboardType = KeyboardType.Text
                )
            }
        }

        item {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AgreeCheckbox(
                    text = "우리가 개인정보 수집 및 이용 동의 (필수)",
                    checked = privacyCheckBoxState.value,
                    onCheckChange = {
                        privacyCheckBoxState.value = !privacyCheckBoxState.value
                        viewModel.fetchIsPrivacyChecked(privacyCheckBoxState.value)
                    }
                )
            }
        }

        item {

            PositiveButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                text = "계정 만들기",
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                onClick = {
                    viewModel.postSocialLogin(
                        email = emailAddressTextState.value,
                        userName = nameTextState.value,
                        nickName = nickNameTextState.value,
                        privacyCheckBoxState = privacyCheckBoxState.value
                    )
                }
            )
        }

    }

    if (state.isLoading) {
        CircularProgress()
    }

    ToastAnimation(yOffset = toastYOffset, toastMessage = toastMessage)

}


@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: AuthSideEffect,
    context: Context,
    onShowToast: (String) -> Unit
) {
    when (sideEffect) {
        is AuthSideEffect.Finish -> {
            val activity = (context as? Activity)
            activity?.finish()
        }

        is AuthSideEffect.ToastMessage -> {
            onShowToast(sideEffect.toastMessage)
        }

        is AuthSideEffect.NavigateToMainActivity -> {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        is AuthSideEffect.NavigateToAccountBookGenerationActivity -> {
            val intent = Intent(context, AccountBookSelectionActivity::class.java)
            context.startActivity(intent)
        }

        else -> {}
    }
}

