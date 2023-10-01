package com.uliga.app.view.auth

import androidx.lifecycle.ViewModel
import com.uliga.domain.AuthType
import com.uliga.domain.SocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val socialLoginUseCase: SocialLoginUseCase
) : ContainerHost<AuthUiState, AuthSideEffect>, ViewModel() {

    override val container = container<AuthUiState, AuthSideEffect>(AuthUiState.empty())

    init {

    }

    fun socialLogin() = intent {
        reduce { state.copy(isLoading = true) }

    }

    fun login(type: AuthType, checkedIdToken: String?) = intent {
        reduce { state.copy(isLoading = true) }
        val socialLoginResult = socialLoginUseCase(type, checkedIdToken).getOrThrow()

        reduce {
            state.copy(
                isLoading = true
            )
        }
    }
}