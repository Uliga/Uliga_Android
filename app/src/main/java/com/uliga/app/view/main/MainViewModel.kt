package com.uliga.app.view.main

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthAssetUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import com.uliga.domain.usecase.userAuth.local.UpdateIsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchIdUseCase: FetchIdUseCase,
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val getAccountBookMonthAssetUseCase: GetAccountBookMonthAssetUseCase,
    private val updateIsLoginUseCase: UpdateIsLoginUseCase
) : ContainerHost<MainUiState, MainSideEffect>, BaseViewModel() {
    override val container = container<MainUiState, MainSideEffect>(
        MainUiState.empty()
    )

    init {
        updateIsLogin()
        fetchCurrentAccountBookInfo()
        fetchId()
    }

    private fun updateIsLogin() = intent {
        launch {
            updateIsLoginUseCase(true)
        }
    }

    private fun fetchCurrentAccountBookInfo() = intent {
        launch {

            val currentDate = LocalDate.now()

            fetchCurrentAccountBookInfoUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            currentAccountInfo = it
                        )
                    }

                    getAccountBookMonthAssetUseCase(
                        isCurrent = true,
                        accountBookId = it.second,
                        month = currentDate.monthValue,
                        year = currentDate.year
                    )
                }
        }
    }

    private fun fetchId() = intent {
        fetchIdUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        id = it
                    )
                }
            }

    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(MainSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean): Job = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}