package com.uliga.app.view.auth

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.uliga.app.base.ComposeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ComposeViewModel<AuthUiState, AuthUiEvent>(savedStateHandle) {

    override fun updateEvent(event: AuthUiEvent) = launch {
        uiEvent.emit(event)
    }

    override fun createInitialState(savedState: Parcelable?): AuthUiState {
        return AuthUiState()
    }
}