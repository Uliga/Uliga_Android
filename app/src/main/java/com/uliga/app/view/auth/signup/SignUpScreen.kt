package com.uliga.app.view.auth.signup

import android.content.Intent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.uliga.app.view.auth.AuthUiEvent
import com.uliga.app.view.auth.AuthUiState
import com.uliga.app.view.main.MainActivity


@Composable
fun SignUpScreen(
    navController: NavController,
    data: AuthUiState,
    onEvent: (AuthUiEvent) -> Unit
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