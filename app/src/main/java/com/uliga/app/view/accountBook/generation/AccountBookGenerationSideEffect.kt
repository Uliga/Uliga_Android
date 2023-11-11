package com.uliga.app.view.accountBook.generation

sealed class AccountBookGenerationSideEffect {

    object Finish : AccountBookGenerationSideEffect()

    object AddEmailChip : AccountBookGenerationSideEffect()

    data class ToastMessage(val toastMessage: String) : AccountBookGenerationSideEffect()

}