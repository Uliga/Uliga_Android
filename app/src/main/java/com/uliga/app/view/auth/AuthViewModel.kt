package com.uliga.app.view.auth

import com.uliga.app.base.BaseViewModel
import com.uliga.app.ext.isEmailValid
import com.uliga.app.ext.isNickNameTypeValid
import com.uliga.app.utils.ToastMessages.APPLICATION_PASSWORD
import com.uliga.app.utils.ToastMessages.EMAIL
import com.uliga.app.utils.ToastMessages.EMAIL_NO_INFO
import com.uliga.app.utils.ToastMessages.EMAIL_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.MAIL
import com.uliga.app.utils.ToastMessages.NICKNAME
import com.uliga.app.utils.ToastMessages.NICKNAME_DUPLICATE_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.NICKNAME_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.PRIVACY_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.USER_NAME_PLEASE_CONFIRM
import com.uliga.domain.AuthType
import com.uliga.domain.model.userAuth.NormalLoginRequest
import com.uliga.domain.model.userAuth.SocialLoginRequest
import com.uliga.domain.usecase.userAuth.local.UpdateIsLoginUseCase
import com.uliga.domain.usecase.userAuth.remote.GetUserAuthDataExistedUseCase
import com.uliga.domain.usecase.userAuth.remote.PostNormalLoginUseCase
import com.uliga.domain.usecase.userAuth.remote.PostSocialLoginUseCase
import com.uliga.domain.usecase.userAuth.remote.SocialLoginUseCase
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
    private val postNormalLoginUseCase: PostNormalLoginUseCase,
    private val updateIsLoginUseCase: UpdateIsLoginUseCase
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

            getUserAuthDataExistedUseCase(MAIL, socialLoginEmail).onSuccess {
                if (it.exists == null) {
                    postSideEffect(AuthSideEffect.ToastMessage(EMAIL_NO_INFO))
                    return@onSuccess
                }

                if (it.exists!!) {

                    val normalLoginRequest = NormalLoginRequest(
                        email = socialLoginEmail,
                        password = APPLICATION_PASSWORD
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
                postSideEffect(AuthSideEffect.ToastMessage(NICKNAME_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            getUserAuthDataExistedUseCase(NICKNAME, nickName)
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
                postSideEffect(AuthSideEffect.ToastMessage(EMAIL_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (userName.isEmpty()) {
                postSideEffect(AuthSideEffect.ToastMessage(USER_NAME_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (nickName.isEmpty() || !isNickNameTypeValid(nickName)) {
                postSideEffect(AuthSideEffect.ToastMessage(NICKNAME_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (state.isNickNameExisted != false) {
                postSideEffect(AuthSideEffect.ToastMessage(NICKNAME_DUPLICATE_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (!privacyCheckBoxState) {
                postSideEffect(AuthSideEffect.ToastMessage(PRIVACY_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            val socialLoginRequest = SocialLoginRequest(
                email = email,
                userName = userName,
                nickName = nickName,
                loginType = EMAIL,
                applicationPassword = APPLICATION_PASSWORD
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