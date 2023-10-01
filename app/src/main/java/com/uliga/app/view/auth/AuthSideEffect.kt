package com.uliga.app.view.auth

sealed class AuthSideEffect {

    object Finish : AuthSideEffect()

    object NavigateToSign
}