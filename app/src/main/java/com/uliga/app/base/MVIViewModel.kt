package com.uliga.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uliga.app.UligaErrorType
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MVIViewModel<State, SideEffect>: ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State

    private val _sideEffect = MutableSharedFlow<SideEffect>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sideEffect = _sideEffect.asSharedFlow()

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _errorEffect = MutableSharedFlow<Pair<String, UligaErrorType>?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val errorEffect = _errorEffect.asSharedFlow()

    val state get() = uiState.value

    protected fun reduce(action: State.() -> State) {
        _uiState.update(action)
    }

    protected suspend fun postSideEffect(sideEffect: SideEffect) {
        _sideEffect.emit(sideEffect)
    }

    fun postToastEffect(message: String) = viewModelScope.launch {
        _errorEffect.emit(Pair(message, UligaErrorType.TOAST))
    }

    private fun filterException(throwable: Throwable) {
        val exception = throwable as? Exception

        when {
            throwable.message == "" -> return
            else -> postToastEffect(exception?.message ?: "알 수 없는 에러")
        }
    }

    fun Result<*>.catchException(
        additional: suspend (exception: Throwable) -> Unit = {}
    ) = viewModelScope.launch {
        onFailure {  exception ->
            filterException(exception)
            additional(exception)
        }
    }

}