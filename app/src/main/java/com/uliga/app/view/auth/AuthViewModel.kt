package com.uliga.app.view.auth

import android.util.Log
import com.uliga.app.base.BaseViewModel
import com.uliga.app.ext.isEmailValid
import com.uliga.app.ext.isNickNameTypeValid
import com.uliga.domain.AuthType
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.usecase.userAuth.GetUserAuthDataExistedUseCase
import com.uliga.domain.usecase.userAuth.PostNormalLoginUseCase
import com.uliga.domain.usecase.userAuth.PostSocialLoginUseCase
import com.uliga.domain.usecase.userAuth.SocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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
) : ContainerHost<AuthUiState, AuthSideEffect>, BaseViewModel() {

    override val container = container<AuthUiState, AuthSideEffect>(AuthUiState.empty())

    fun socialLogin(
        type: AuthType,
        checkedIdToken: String?,
        checkedEmail: String?,
        checkedName: String?
    ) = intent {
        launch {

            val socialLoginResult =
                socialLoginUseCase(type, checkedIdToken, checkedEmail, checkedName).getOrThrow()
            val socialLoginEmail = socialLoginResult.email ?: ""
            val socialLoginName = socialLoginResult.name ?: ""

            getUserAuthDataExistedUseCase("mail", socialLoginEmail).onSuccess {
                if (it.exists == null) {
                    postSideEffect(AuthSideEffect.ToastMessage("이메일에 대한 정보를 불러올 수 없습니다."))
                    return@onSuccess
                }

                if (it.exists!!) {

                    val normalLoginRequest = NormalLoginRequest(
                        email = socialLoginEmail,
                        password = "12341234"
                    )

                    postNormalLoginUseCase(normalLoginRequest).onSuccess {
                        postSideEffect(AuthSideEffect.Finish)
                        postSideEffect(AuthSideEffect.NavigateToMainActivity)
                    }


                } else {
                    postSideEffect(
                        AuthSideEffect.NavigateToSocialSignUpScreen(
                            socialLoginEmail, socialLoginName
                        )
                    )
                }
            }

        }
    }

    fun getIsNickNameExisted(nickName: String) = intent {
        launch {
            if (nickName.isEmpty() || !isNickNameTypeValid(nickName)) {
                postSideEffect(AuthSideEffect.ToastMessage("입력된 닉네임을 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            getUserAuthDataExistedUseCase("nickname", nickName)
                .onSuccess {

                    reduce {
                        state.copy(
                            isNickNameExisted = it.exists
                        )
                    }
                }
        }

    }

    fun fetchIsPrivacyChecked(isPrivacyChecked: Boolean) = intent {
        launch {
            reduce {
                state.copy(
                    isPrivacyChecked = isPrivacyChecked
                )
            }
        }
    }

    fun postSocialLogin(
        email: String,
        userName: String,
        nickName: String,
        privacyCheckBoxState: Boolean
    ) = intent {
        launch {
            if (email.isEmpty() || !isEmailValid(email)) {
                postSideEffect(AuthSideEffect.ToastMessage("입력된 이메일을 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if (userName.isEmpty()) {
                postSideEffect(AuthSideEffect.ToastMessage("입력된 사용자 이름을 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if (nickName.isEmpty() || !isNickNameTypeValid(nickName)) {
                postSideEffect(AuthSideEffect.ToastMessage("입력된 닉네임을 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if(state.isNickNameExisted != false) {
                postSideEffect(AuthSideEffect.ToastMessage("닉네임 중복 여부를 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if (!privacyCheckBoxState) {
                postSideEffect(AuthSideEffect.ToastMessage("권한 정보를 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            val socialLoginRequest = SocialLoginRequest(
                email = email,
                userName = userName,
                nickName = nickName,
                loginType = "EMAIL",
                applicationPassword = "12341234"
            )

            postSocialLoginUseCase(socialLoginRequest)
                .onSuccess {
                    postSideEffect(AuthSideEffect.Finish)
                    postSideEffect(AuthSideEffect.NavigateToAccountBookGenerationActivity)
                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AuthSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean): Job = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }


}