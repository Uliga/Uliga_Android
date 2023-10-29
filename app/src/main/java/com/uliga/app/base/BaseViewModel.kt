package com.uliga.app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uliga.data_remote.UligaResponseException
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val ceh = CoroutineExceptionHandler { _, throwable ->

        updateIsLoading(false)
        filterUligaException(throwable)
    }

    inline fun launch(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        @ViewModelScoped crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(ceh + SupervisorJob(), start = start) {
            updateIsLoading(true)
            action(this)
            updateIsLoading(false)
        }
    }

    private fun filterUligaException(throwable: Throwable) {
        val exception = throwable as? UligaResponseException

        if (exception == null) onShowErrorToast("알 수 없는 오류가 발생했습니다.")
        else onShowErrorToast(exception.message)
    }

    abstract fun onShowErrorToast(message: String): Job

    abstract fun updateIsLoading(isLoading: Boolean): Job
}