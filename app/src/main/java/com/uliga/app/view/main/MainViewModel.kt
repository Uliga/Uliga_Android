package com.uliga.app.view.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase
) : ContainerHost<MainUiState, MainSideEffect>, ViewModel() {
    override val container = container<MainUiState, MainSideEffect>(
        MainUiState.empty()
    )

    init {
        fetchAccountBook()
    }

    fun fetchAccountBook() = intent {
        fetchCurrentAccountBookInfoUseCase().onSuccess {
            Log.d("fetchAccountBook", it.toString())
        }
    }
}