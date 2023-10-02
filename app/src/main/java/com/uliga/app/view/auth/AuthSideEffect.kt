package com.uliga.app.view.auth

sealed class AuthSideEffect {

    object Finish : AuthSideEffect()

    object NavigateToMainActivity: AuthSideEffect()

    object NavigateToSignUpScreen: AuthSideEffect()

    data class ToastMessage(val toastMessage: String): AuthSideEffect()
}