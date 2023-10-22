package com.uliga.app.view.profile

sealed class ProfileSideEffect {

    data class ToastMessage(val toastMessage: String) : ProfileSideEffect()

}