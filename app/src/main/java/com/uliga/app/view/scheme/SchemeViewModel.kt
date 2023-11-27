package com.uliga.app.view.scheme

import com.uliga.app.base.BaseViewModel
import com.uliga.app.view.home.HomeSideEffect
import com.uliga.domain.usecase.userAuth.local.FetchIsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SchemeViewModel @Inject constructor(
    private val fetchIsLoginUseCase: FetchIsLoginUseCase
) : ContainerHost<SchemeUiState, SchemeSideEffect>, BaseViewModel() {

    override val container = container<SchemeUiState, SchemeSideEffect>(SchemeUiState.empty())

    init {
        fetchIsLogin()
    }

    private fun fetchIsLogin() = intent {
        fetchIsLoginUseCase()
            .onSuccess { isLogin ->
                if(isLogin) {
                    postSideEffect(SchemeSideEffect.MoveToMainActivity)
                } else {
                    postSideEffect(SchemeSideEffect.MoveToAuthActivity)
                }
            }.onFailure {
                postSideEffect(SchemeSideEffect.MoveToAuthActivity)
            }
    }



    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(SchemeSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}