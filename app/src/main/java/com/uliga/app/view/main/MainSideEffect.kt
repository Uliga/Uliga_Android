package com.uliga.app.view.main

sealed class MainSideEffect {

    object Finish : MainSideEffect()

    data class ToastMessage(val toastMessage: String) : MainSideEffect()

}