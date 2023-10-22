package com.uliga.app.view.main

import androidx.lifecycle.ViewModel
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchIdUseCase: FetchIdUseCase,
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val getMemberUseCase: GetMemberUseCase
) : ContainerHost<MainUiState, MainSideEffect>, ViewModel() {
    override val container = container<MainUiState, MainSideEffect>(
        MainUiState.empty()
    )

    init {
        fetchCurrentAccountBookInfo()
        fetchId()
        getMember()
    }

    fun fetchCurrentAccountBookInfo() = intent {
        fetchCurrentAccountBookInfoUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountInfo = it
                    )
                }
            }
            .onFailure {

            }
    }

    fun fetchId() = intent {
        fetchIdUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        id = it
                    )
                }
            }
            .onFailure {

            }
    }

    fun getMember() = intent {
        getMemberUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        member = it
                    )
                }
            }
            .onFailure {

            }
    }
}