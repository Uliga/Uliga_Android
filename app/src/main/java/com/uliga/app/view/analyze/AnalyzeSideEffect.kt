package com.uliga.app.view.analyze

sealed class AnalyzeSideEffect {

    data class ToastMessage(val toastMessage: String) : AnalyzeSideEffect()

}