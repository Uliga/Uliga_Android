package com.uliga.app.view.auth.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.uliga.app.R
import com.uliga.app.view.component.toast.TOAST_DURATION_MILLIS
import com.uliga.app.view.component.toast.TOAST_END_POSITION
import com.uliga.app.view.component.toast.TOAST_START_POSITION
import com.uliga.app.view.component.toast.TopDownToast
import com.uliga.app.ext.getGoogleSignInClient
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.CustomGrey700
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey600
import com.uliga.app.ui.theme.KakaoYellow
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.ext.CircularProgress
import com.uliga.app.view.auth.AuthActivity
import com.uliga.app.view.auth.AuthSideEffect
import com.uliga.app.view.auth.AuthViewModel
import com.uliga.app.view.component.LoginButton
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.main.MainActivity
import com.uliga.domain.AuthType
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    val activityResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(intent)

                viewModel.socialLogin(
                    AuthType.GOOGLE,
                    task.result.idToken,
                    task.result.email,
                    task.result.displayName
                )
            } else {
                viewModel.onShowErrorToast("구글 로그인에 실패했습니다.")
            }
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
            navController = navController,
            onShowToast = {
                isToastAnimating = true
                toastMessage = it
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(

                modifier = Modifier
                    .size(72.dp),
                painter = painterResource(
                    id = R.drawable.uliga_logo
                ),
                contentDescription = null
            )

            VerticalSpacer(20.dp)

            Text(
                text = "우리가에 오신것을 환영합니다 \uD83D\uDE46\uD83C\uDFFB\u200D♀️",
                color = Grey600,
                style = UligaTheme.typography.title1,
                maxLines = 2,
            )

            VerticalSpacer(20.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
                    .background(
                        color = CustomGrey100,
                        shape = UligaTheme.shapes.medium
                    ),
            ) {

                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                    text = "우리가란 '우리의 가계부' 의 줄임말으로, 공유 가계부 데스크톱 애플리케이션입니다.\n" +
                            "우리가로 가족, 룸메이트, 연인과 함께 지출관리를 해보세요!",
                    textAlign = TextAlign.Start,
                    color = Grey600,
                    style = UligaTheme.typography.body1,
                )
            }

            VerticalSpacer(32.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f),
                    color = Grey400,
                    thickness = 1.dp
                )

                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1.5f),
                    text = "SNS로 간편하게 로그인",
                    color = CustomGrey700,
                    style = UligaTheme.typography.subTitle2,
                    maxLines = 2
                )

                Divider(
                    modifier = Modifier
                        .weight(1f),
                    color = Grey400,
                    thickness = 1.dp
                )
            }

            VerticalSpacer(16.dp)

            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoginButton(
                    text = "카카오로 로그인",
                    icon = painterResource(
                        id = R.drawable.ic_kakao
                    ),
                    imageSize = 32.dp,
                    backgroundColor = KakaoYellow,
                    onClick = {
                        viewModel.socialLogin(AuthType.KAKAO, null, null, null)
                    }
                )

                VerticalSpacer(height = 12.dp)

                LoginButton(
                    text = "구글로 로그인    ",
                    icon = painterResource(
                        id = R.drawable.ic_google
                    ),
                    backgroundColor = White,
                    imageSize = 32.dp,
                    onClick = {
                        activityResult.launch(getGoogleSignInClient(context).signInIntent)
                    }
                )
            }
        }

    }

    if (state.isLoading) {
        CircularProgress()
    }

    TopDownToast(
        toastYOffset = toastYOffset,
        toastMessage = toastMessage
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun handleSideEffect(
    sideEffect: AuthSideEffect,
    context: Context,
    navController: NavController,
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

        is AuthSideEffect.NavigateToNormalSignUpScreen -> {
            navController.navigate(AuthActivity.AuthRoute.NORMAL_SIGNUP.route)
        }

        is AuthSideEffect.NavigateToSocialSignUpScreen -> {
            navController.navigate(AuthActivity.AuthRoute.SOCIAL_SIGNUP.route + "/${sideEffect.email}/${sideEffect.name}")
        }

        is AuthSideEffect.NavigateToMainActivity -> {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        else -> {
            // no-op
        }

    }
}
