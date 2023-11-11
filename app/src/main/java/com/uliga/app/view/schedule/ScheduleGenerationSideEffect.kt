package com.uliga.app.view.schedule

sealed class ScheduleGenerationSideEffect {

    object Finish : ScheduleGenerationSideEffect()

    data class ToastMessage(val toastMessage: String) : ScheduleGenerationSideEffect()

}