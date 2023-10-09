package com.uliga.app.view.auth

import androidx.lifecycle.ViewModel
import com.uliga.domain.AuthType
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.usecase.userAuth.GetUserAuthDataExistedUseCase
import com.uliga.domain.usecase.userAuth.PostNormalLoginUseCase
import com.uliga.domain.usecase.userAuth.PostSocialLoginUseCase
import com.uliga.domain.usecase.userAuth.SocialLoginUseCase
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
    private val getUserAuthDataExistedUseCase: GetUserAuthDataExistedUseCase,
    private val postSocialLoginUseCase: PostSocialLoginUseCase,
    private val postNormalLoginUseCase: PostNormalLoginUseCase
) : ContainerHost<AuthUiState, AuthSideEffect>, ViewModel() {

    override val container = container<AuthUiState, AuthSideEffect>(AuthUiState.empty())

    init {

    }


    fun socialLogin(
        type: AuthType,
        checkedIdToken: String?,
        checkedEmail: String?,
        checkedName: String?
    ) = intent {
        reduce { state.copy(isLoading = true) }
        val socialLoginResult =
            socialLoginUseCase(type, checkedIdToken, checkedEmail, checkedName).getOrThrow()
        val socialLoginEmail = socialLoginResult.email ?: ""
        val socialLoginName = socialLoginResult.name ?: ""

        getUserAuthDataExistedUseCase("mail", socialLoginEmail).onSuccess {
            if (it.exists == null) {
                postSideEffect(AuthSideEffect.ToastMessage("이메일에 대한 정보를 불러올 수 없습니다."))
                return@intent
            }

            if (it.exists!!) {

                val normalLoginRequest = NormalLoginRequest(
                    email = socialLoginEmail,
                    password = "12341234"
                )

                postNormalLoginUseCase(normalLoginRequest).onSuccess {
                    postSideEffect(AuthSideEffect.Finish)
                    postSideEffect(AuthSideEffect.NavigateToMainActivity)
                }.onFailure {

                }


            } else {
                postSideEffect(
                    AuthSideEffect.NavigateToSocialSignUpScreen(
                        socialLoginEmail, socialLoginName
                    )
                )
            }

        }.onFailure { throwable ->
            reduce {
                state.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun getIsNickNameExisted(nickName: String) = intent {
        if(nickName.isEmpty()) {
            postSideEffect(AuthSideEffect.ToastMessage("닉네임을 적어주세요."))
            return@intent
        }

        getUserAuthDataExistedUseCase("nickname", nickName)
            .onSuccess {
                reduce {
                    state.copy(isLoading = true)
                }
                reduce {
                    state.copy(
                        isLoading = false,
                        isNickNameExisted = it.exists
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
            }
    }

    fun fetchIsPrivacyChecked(isPrivacyChecked: Boolean) = intent {
        reduce {
            state.copy(
                isPrivacyChecked = isPrivacyChecked
            )
        }
    }

    fun postSocialLogin(
        email: String,
        userName: String,
        nickName: String,
        privacyCheckBoxState: Boolean
    ) = intent {

        if(!privacyCheckBoxState) {

            return@intent
        }

        val socialLoginRequest = SocialLoginRequest(
            email = email,
            userName = userName,
            nickName = nickName,
            loginType = "EMAIL",
            applicationPassword = "12341234"
        )

        postSideEffect(AuthSideEffect.NavigateToAccountBookGenerationActivity)

        postSocialLoginUseCase(socialLoginRequest)
            .onSuccess {
                postSideEffect(AuthSideEffect.ToastMessage("회원가입에 성공했습니다."))
                postSideEffect(AuthSideEffect.Finish)
                postSideEffect(AuthSideEffect.NavigateToAccountBookGenerationActivity)
            }
            .onFailure {

            }
    }
}