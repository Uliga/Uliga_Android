package com.uliga.app.view.finance

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(

) : ContainerHost<FinanceUiState, FinanceSideEffect>, ViewModel() {

    override val container = container<FinanceUiState, FinanceSideEffect>(FinanceUiState.empty())

    init {

    }



}