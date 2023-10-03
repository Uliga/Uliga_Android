package com.uliga.app.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountBooksUseCase: GetAccountBooksUseCase
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeUiState, HomeSideEffect>(HomeUiState.empty())


    init {
        getAccountBooks()
    }

    fun getAccountBooks() = intent {

        getAccountBooksUseCase()
            .onSuccess {
                Log.d("getAccountBooks", it.toString())
            }
            .onFailure {

            }
    }

    override fun onCleared() {
        Log.d("homeViewModel", "onCleared")
        super.onCleared()
    }
}