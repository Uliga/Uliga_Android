package com.uliga.app.view.profile

sealed class ProfileSideEffect {

    object DismissSignOutAlert: ProfileSideEffect()

    object DismissLogoutAlert: ProfileSideEffect()

    object Finish: ProfileSideEffect()

    object MoveToAuthActivity: ProfileSideEffect()

    data class ToastMessage(val toastMessage: String) : ProfileSideEffect()

}