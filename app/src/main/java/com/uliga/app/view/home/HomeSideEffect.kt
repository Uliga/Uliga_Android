package com.uliga.app.view.home

sealed class HomeSideEffect {

    object FinishScheduleBottomSheet: HomeSideEffect()

    data class ToastMessage(val toastMessage: String) : HomeSideEffect()

}