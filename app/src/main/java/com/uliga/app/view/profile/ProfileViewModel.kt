package com.uliga.app.view.profile

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.member.DeleteMemberUseCase
import com.uliga.domain.usecase.userAuth.GetLogoutRedirectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val getLogoutRedirectUseCase: GetLogoutRedirectUseCase,
) : ContainerHost<ProfileUiState, ProfileSideEffect>, BaseViewModel() {
    override val container = container<ProfileUiState, ProfileSideEffect>(ProfileUiState.empty())

    init {

    }

    fun initializeBaseInfo(id: Long?, currentAccountInfo: Pair<String, Long>?, member: Member?) =
        intent {
            launch {
                reduce {
                    state.copy(
                        id = id,
                        currentAccountInfo = currentAccountInfo,
                        member = member
                    )
                }
            }
        }

    fun deleteMember() = intent {
        launch {
            deleteMemberUseCase()
                .onSuccess {
                    postSideEffect(
                        ProfileSideEffect.DismissSignOutAlert
                    )
                    postSideEffect(
                        ProfileSideEffect.MoveToAuthActivity
                    )
                    postSideEffect(
                        ProfileSideEffect.Finish
                    )
                }
        }
    }

    fun getLogoutRedirect() = intent {
        launch {
            getLogoutRedirectUseCase()
                .onSuccess {
                    postSideEffect(
                        ProfileSideEffect.DismissLogoutAlert
                    )
                    postSideEffect(
                        ProfileSideEffect.MoveToAuthActivity
                    )
                    postSideEffect(
                        ProfileSideEffect.Finish
                    )
                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(ProfileSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}