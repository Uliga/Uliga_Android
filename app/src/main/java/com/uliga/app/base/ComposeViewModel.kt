package com.uliga.app.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

abstract class ComposeViewModel<DATA: ComposeViewModel.UiState, Event: ComposeViewModel.UiEvent>(
    private val stateHandle: SavedStateHandle
): ViewModel() {

    val ceh = CoroutineExceptionHandler { _, throwable ->
        updateIsLoading(false)

        updateErrorMessage(throwable)
    }


    private val initialState by lazy {
        createInitialState(stateHandle[KEY_STATE])
    }

    open val uiEvent: MutableEventFlow<UiEvent> = MutableEventFlow()

    private val _showLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    private val _uiState: MutableStateFlow<DATA> = MutableStateFlow(initialState)
    val uiState: StateFlow<DATA> = _uiState.asStateFlow()

    private val _error: MutableSharedFlow<List<String?>> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val error = _error.asSharedFlow()

    inline fun launch(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        @ViewModelScoped crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(ceh, start = start) {
            action(this)
        }
    }

    abstract class UiEvent

    fun updateIsLoading(isLoading: Boolean) = launch {
        _showLoading.emit(isLoading)
    }

    fun updateErrorMessage(error: Throwable) = launch {
        updateErrorMessage(error.message)
    }

    fun updateErrorMessage(errorMessage: String?) = launch {
        updateIsLoading(false)
        val message = if(errorMessage?.isEmpty() == true) "오류가 발생했습니다\n잠시 후 다시 시도해주세요" else errorMessage
        _error.apply {
            resetReplayCache()
            emit(listOf(message))
        }
    }

    abstract fun updateEvent(event: Event): Job
    protected abstract fun createInitialState(savedState: Parcelable?): DATA

    protected fun updateStateData(action: DATA.() -> DATA) {
        updateIsLoading(false)

        try {
            _uiState.updateAndGet(action)
                .also {
                    it.toParcelable()?.run {
                        stateHandle[KEY_STATE] = this
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface UiState {
        fun toParcelable(): Parcelable? = null
    }

    companion object {
        const val KEY_STATE = "keyState"
    }
}