package com.uliga.app.view.auth

import androidx.lifecycle.ViewModel
import com.uliga.domain.AuthType
import com.uliga.domain.usecase.GetUserAuthEmailExistedUseCase
import com.uliga.domain.usecase.SocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val socialLoginUseCase: SocialLoginUseCase,
    private val getUserAuthEmailExistedUseCase: GetUserAuthEmailExistedUseCase
) : ContainerHost<AuthUiState, AuthSideEffect>, ViewModel() {

    override val container = container<AuthUiState, AuthSideEffect>(AuthUiState.empty())

    init {

    }

    fun socialLogin(type: AuthType, checkedIdToken: String?, checkedEmail: String?) = intent {
        reduce { state.copy(isLoading = true) }
        val socialLoginResult = socialLoginUseCase(type, checkedIdToken, checkedEmail).getOrThrow()
        val socialLoginEmail = socialLoginResult.email

        if(socialLoginEmail == null) {

            reduce {
                state.copy(
                    isLoading = false
                )
            }

            return@intent
        } else {
            getUserAuthEmailExistedUseCase(socialLoginEmail).onSuccess {
                if(it.exists == null) {
                    postSideEffect(AuthSideEffect.ToastMessage("이메일에 대한 정보를 불러올 수 없습니다."))
                    return@intent
                }

                if(it.exists!!) {
                    postSideEffect(AuthSideEffect.NavigateToMainActivity)
                } else {
                    postSideEffect(AuthSideEffect.NavigateToSocialSignUpScreen(socialLoginEmail))
                }

            }.onFailure { throwable ->
                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
            }

        }



    }
}