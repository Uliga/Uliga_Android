package com.uliga.app.view.auth

sealed class AuthSideEffect {

    object Finish : AuthSideEffect()

    object NavigateToMainActivity: AuthSideEffect()

    object NavigateToNormalSignUpScreen: AuthSideEffect()

    data class NavigateToSocialSignUpScreen(val email: String): AuthSideEffect()

    data class ToastMessage(val toastMessage: String): AuthSideEffect()
}