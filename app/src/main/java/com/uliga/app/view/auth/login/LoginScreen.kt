package com.uliga.app.view.auth.login

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.uliga.app.view.auth.AuthRoute
import com.uliga.app.view.auth.AuthUiEvent
import com.uliga.app.view.auth.AuthUiState
import com.uliga.app.view.main.MainUiState


@Composable
fun LoginScreen(
    navController: NavController,
    data: AuthUiState,
    onEvent: (AuthUiEvent) -> Unit
) {
    
    val context = LocalContext.current
    
    Text(
        text = "로그인"
    )
    
    Button(onClick = {
        navController.navigate(AuthRoute.SIGNUP.route)
    }) {
        
    }
    
}

