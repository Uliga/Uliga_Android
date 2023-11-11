package com.uliga.app.view.home

sealed class HomeSideEffect {

    object FinishBudgetSettingBottomSheet: HomeSideEffect()

    data class ToastMessage(val toastMessage: String) : HomeSideEffect()

}