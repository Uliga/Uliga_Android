package com.uliga.app.view.main

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.uliga.app.base.ComposeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ComposeViewModel<MainUiState, MainUiEvent>(savedStateHandle) {
    override fun updateEvent(event: MainUiEvent) = launch {
        uiEvent.emit(event)
    }

    override fun createInitialState(savedState: Parcelable?): MainUiState {
        return MainUiState()
    }
}