package com.uliga.app.view.profile

import androidx.lifecycle.ViewModel
import com.uliga.domain.model.member.Member
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ContainerHost<ProfileUiState, ProfileSideEffect>, ViewModel() {
    override val container = container<ProfileUiState, ProfileSideEffect>(ProfileUiState.empty())

    init {

    }

    fun initializeBaseInfo(id: Long?, currentAccountInfo: Pair<String, Long>?, member: Member?) =
        intent {
            reduce {
                state.copy(
                    id = id,
                    currentAccountInfo = currentAccountInfo,
                    member = member
                )
            }
        }
}