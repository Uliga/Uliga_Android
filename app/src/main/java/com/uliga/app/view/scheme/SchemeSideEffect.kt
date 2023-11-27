package com.uliga.app.view.scheme

sealed class SchemeSideEffect {

    object MoveToAuthActivity : SchemeSideEffect()

    object MoveToMainActivity : SchemeSideEffect()

    data class ToastMessage(val toastMessage: String) : SchemeSideEffect()

}