package com.uliga.app.view.home

sealed class HomeSideEffect {

    data class ToastMessage(val toastMessage: String) : HomeSideEffect()

}