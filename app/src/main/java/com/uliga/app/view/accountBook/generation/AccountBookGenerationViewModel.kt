package com.uliga.app.view.accountBook.generation

import androidx.lifecycle.ViewModel
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookGenerationViewModel @Inject constructor(
    private val getAccountBooksUseCase: GetAccountBooksUseCase
): ContainerHost<AccountBookGenerationUiState, AccountBookGenerationSideEffect>, ViewModel() {
   override val container = container<AccountBookGenerationUiState, AccountBookGenerationSideEffect>(AccountBookGenerationUiState.empty())




}