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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uliga.app.R
import com.uliga.app.ToastAnimation
import com.uliga.app.ui.theme.Black
import com.uliga.app.ui.theme.CustomGrey100
import com.uliga.app.ui.theme.CustomGrey200
import com.uliga.app.ui.theme.CustomGrey600
import com.uliga.app.ui.theme.Grey300
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.CircularProgress
import com.uliga.app.view.accountBook.generation.AccountBookGenerationActivity
import com.uliga.app.view.auth.AuthSideEffect
import com.uliga.app.view.auth.AuthViewModel
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
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {

        item {

            CenterAlignedTopAppBar(
                modifier = Modifier.background(Color.White),
                title = {
                    Row {
                        Image(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(
                                id = R.drawable.uliga_logo
                            ),
                            contentDescription = "uliga logo"
                        )

                        Text(
                            modifier = Modifier.padding(
                                end = 12.dp
                            ),
                            text = "우리가",
                            color = Primary,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
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
                    color = Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                Text(
                    text = "만나서 반가워요! 당신의 건강한 소비 생활을 응원합니다\uD83D\uDE4B\u200D♀️",
                    color = CustomGrey600,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 2
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
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
                    text = "이메일 주소",
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )



                TextField(
                    modifier = Modifier
                        .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    value = emailAddressTextState.value,
                    onValueChange = {
                        emailAddressTextState.value = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
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
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                TextField(
                    modifier = Modifier
                        .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    value = nameTextState.value,
                    onValueChange = {
                        nameTextState.value = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
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
                    text = "닉네임",
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )



                TextField(
                    modifier = Modifier
                        .border(width = 1.dp, color = Grey400, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(),
                    value = nickNameTextState.value,
                    onValueChange = {
                        nickNameTextState.value = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 20.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                )

                Button(
                    enabled = state.isNickNameExisted == null || state.isNickNameExisted,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 4.dp),
                    contentPadding = PaddingValues(
                        vertical = 4.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor =
                        if (state.isNickNameExisted == null || state.isNickNameExisted) CustomGrey200
                        else CustomGrey100,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        viewModel.getIsNickNameExisted(nickNameTextState.value)
                    }) {
                    Text(
                        color = if (state.isNickNameExisted == null || state.isNickNameExisted) Primary
                        else Grey300,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        text = "확인"
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = privacyCheckBoxState.value,
                    onCheckedChange = {
                        privacyCheckBoxState.value = !privacyCheckBoxState.value
                        viewModel.fetchIsPrivacyChecked(privacyCheckBoxState.value)
                    }
                )

                Text(
                    color = CustomGrey600,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    text = "우리가 개인정보 수집 및 이용 동의 (필수)",
                )

            }
        }

        item {

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Primary,
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                  viewModel.postSocialLogin(
                      email = emailAddressTextState.value,
                      userName = nameTextState.value,
                      nickName = nickNameTextState.value,
                      privacyCheckBoxState = privacyCheckBoxState.value
                  )

                }) {
                Text(
                    color = Color.White,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    text = "계정 만들기"
                )
            }


        }

    }

    if(state.isLoading) {
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
            val intent = Intent(context, AccountBookGenerationActivity::class.java)
            context.startActivity(intent)
        }
        else -> {}
    }
}

