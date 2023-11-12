package com.uliga.app.view.accountBook.selection

sealed class AccountBookSelectionSideEffect {

    object NavigateToMainActivity: AccountBookSelectionSideEffect()

    object NavigateToLoginScreen: AccountBookSelectionSideEffect()

    data class ToastMessage(val toastMessage: String): AccountBookSelectionSideEffect()


}