package com.uliga.app.view.accountBook.generation

import com.uliga.app.view.auth.AuthSideEffect

sealed class AccountBookGenerationSideEffect {

    object Finish : AccountBookGenerationSideEffect()

    object NavigateToMainActivity: AccountBookGenerationSideEffect()

    object FinishAccountBookGenerationBottomSheet: AccountBookGenerationSideEffect()

    object AddEmailChip: AccountBookGenerationSideEffect()

    data class ToastMessage(val toastMessage: String): AccountBookGenerationSideEffect()


}