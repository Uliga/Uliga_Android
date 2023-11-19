package com.uliga.app.view.profile

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.member.DeleteMemberUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
import com.uliga.domain.usecase.userAuth.GetLogoutRedirectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
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
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase,
    private val getMemberUseCase: GetMemberUseCase
) : ContainerHost<ProfileUiState, ProfileSideEffect>, BaseViewModel() {
    override val container = container<ProfileUiState, ProfileSideEffect>(ProfileUiState.empty())

    init {
        initialize()
    }

    fun initialize() {
        observeCurrentAccountBookId()
    }

    fun observeCurrentAccountBookId() = intent {
        launch {
            fetchCurrentAccountBookIdUseCase().collectLatest { accountBookId ->
                updateAccountBookId(accountBookId)

                getMember()
            }
        }
    }

    private suspend fun getMember() = intent {
        launch {
            getMemberUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            member = it
                        )
                    }
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

    suspend fun updateAccountBookId(accountBookId: Long?) = intent {
        launch {
            reduce {
                state.copy(
                    accountBookId = accountBookId
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