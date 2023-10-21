package com.uliga.app.view.accountBook.input

sealed class AccountBookForInputSideEffect {

    object Finish : AccountBookForInputSideEffect()

    data class ToastMessage(val toastMessage: String) : AccountBookForInputSideEffect()

}