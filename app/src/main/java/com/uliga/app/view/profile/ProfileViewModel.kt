package com.uliga.app.view.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ContainerHost<ProfileUiState, ProfileSideEffect>, ViewModel() {
    override val container = container<ProfileUiState, ProfileSideEffect>(ProfileUiState.empty())


}