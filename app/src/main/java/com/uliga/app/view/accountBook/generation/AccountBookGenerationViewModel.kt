package com.uliga.app.view.accountBook.generation

import androidx.lifecycle.ViewModel
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.member.DeleteMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookGenerationViewModel @Inject constructor(
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase
) : ContainerHost<AccountBookGenerationUiState, AccountBookGenerationSideEffect>, ViewModel() {
    override val container =
        container<AccountBookGenerationUiState, AccountBookGenerationSideEffect>(
            AccountBookGenerationUiState.empty()
        )

    init {
        getAccountBooks()
    }

    fun deleteMember() = intent {
        deleteMemberUseCase()
    }

    fun getAccountBooks() = intent {

        getAccountBooksUseCase()
            .onSuccess {
                reduce { state.copy(isLoading = true) }
                reduce {
                    state.copy(
                        accountBooks = it
                    )
                }
            }
            .onFailure {

            }
    }


}