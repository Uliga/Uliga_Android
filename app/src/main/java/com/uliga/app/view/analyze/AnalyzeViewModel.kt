package com.uliga.app.view.analyze

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AnalyzeViewModel @Inject constructor(
) : ContainerHost<AnalyzeUiState, AnalyzeSideEffect>, ViewModel() {

    override val container = container<AnalyzeUiState, AnalyzeSideEffect>(AnalyzeUiState.empty())

    init {

    }

}