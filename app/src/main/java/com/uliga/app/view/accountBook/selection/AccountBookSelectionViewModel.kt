package com.uliga.app.view.accountBook.selection

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.model.accountBook.AccountBooks
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookUseCase
import com.uliga.domain.usecase.accountbook.local.UpdateAccountBookUseCase
import com.uliga.domain.usecase.member.DeleteMemberUseCase
import com.uliga.domain.usecase.userAuth.GetUserAuthDataExistedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookSelectionViewModel @Inject constructor(
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val updateAccountBookUseCase: UpdateAccountBookUseCase
) : ContainerHost<AccountBookSelectionUiState, AccountBookSelectionSideEffect>, BaseViewModel() {
    override val container =
        container<AccountBookSelectionUiState, AccountBookSelectionSideEffect>(
            AccountBookSelectionUiState.empty()
        )

    init {
        getAccountBooks()
    }

    fun deleteMember() = intent {
        launch {
            deleteMemberUseCase().onSuccess {
                postSideEffect(AccountBookSelectionSideEffect.NavigateToLoginScreen)
            }
        }
    }

    fun getAccountBooks() = intent {
        launch {

            getAccountBooksUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            accountBooks = it
                        )
                    }
                }
        }
    }

    fun updateAccountBook(currentIndexState: Int, accountBooks: AccountBooks?) = intent {
        launch {
            if (currentIndexState == -1) {
                postSideEffect(AccountBookSelectionSideEffect.ToastMessage("가계부를 선택해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if (accountBooks == null) {
                postSideEffect(AccountBookSelectionSideEffect.ToastMessage("가계부를 찾을 수 없습니다."))
                updateIsLoading(false)
                return@launch
            }

            val accountBookName = accountBooks.accountBooks[currentIndexState].info.accountBookName
            val accountBookId = accountBooks.accountBooks[currentIndexState].info.accountBookId

            updateAccountBookUseCase(accountBookName, accountBookId)
                .onSuccess {
                    postSideEffect(AccountBookSelectionSideEffect.NavigateToMainActivity)
                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AccountBookSelectionSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}