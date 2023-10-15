package com.uliga.app.view.finance

sealed class FinanceSideEffect {

    data class ToastMessage(val toastMessage: String) : FinanceSideEffect()

}