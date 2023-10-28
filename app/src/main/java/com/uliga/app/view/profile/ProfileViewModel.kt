package com.uliga.app.view.profile

import androidx.lifecycle.ViewModel
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
) : ContainerHost<ProfileUiState, ProfileSideEffect>, ViewModel() {
    override val container = container<ProfileUiState, ProfileSideEffect>(ProfileUiState.empty())

    init {

    }

    fun initializeBaseInfo(id: Long?, currentAccountInfo: Pair<String, Long>?, member: Member?) =
        intent {
            reduce { state.copy(isLoading = true) }
            reduce {
                state.copy(
                    id = id,
                    currentAccountInfo = currentAccountInfo,
                    member = member
                )
            }
            reduce { state.copy(isLoading = false) }
        }

    fun deleteMember() = intent {
        deleteMemberUseCase()
            .onSuccess {
                reduce { state.copy(isLoading = true) }

                postSideEffect(
                    ProfileSideEffect.DismissSignOutAlert
                )
                postSideEffect(
                    ProfileSideEffect.MoveToAuthActivity
                )
                postSideEffect(
                    ProfileSideEffect.Finish
                )
                reduce { state.copy(isLoading = false) }

            }
            .onFailure {

            }
    }

    fun getLogoutRedirect() = intent {
        getLogoutRedirectUseCase()
            .onSuccess {
                reduce { state.copy(isLoading = true) }
                postSideEffect(
                    ProfileSideEffect.DismissSignOutAlert
                )
                postSideEffect(
                    ProfileSideEffect.MoveToAuthActivity
                )
                postSideEffect(
                    ProfileSideEffect.Finish
                )
                reduce { state.copy(isLoading = false) }

            }
            .onFailure {

            }
    }

}