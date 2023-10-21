package com.uliga.app.view.finance

sealed class FinanceSideEffect {

    object DismissDeleteAlert: FinanceSideEffect()

    data class ToastMessage(val toastMessage: String) : FinanceSideEffect()

}