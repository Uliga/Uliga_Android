package com.uliga.app.view.accountBook.input

sealed class AccountBookInputSideEffect {

    object Finish : AccountBookInputSideEffect()

    data class ToastMessage(val toastMessage: String) : AccountBookInputSideEffect()

}