package com.uliga.app.view.auth.signup

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.uliga.app.view.auth.AuthUiEvent
import com.uliga.app.view.auth.AuthUiState
import com.uliga.app.view.main.MainActivity


@Composable
fun SignUpScreen(
//    navController: NavController,
//    data: AuthUiState,
//    onEvent: (AuthUiEvent) -> Unit
) {

    val context = LocalContext.current

    Text(
        text = "회원가입"
    )

    Button(onClick = {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)

    }) {

    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun PreviewLoginScreen() {
    SignUpScreen()
}