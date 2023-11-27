package com.uliga.app.view.accountBook.generation

import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_NAME_PLEASE_WRITE
import com.uliga.app.utils.ToastMessages.EMAIL_NO_EXISTED
import com.uliga.app.utils.ToastMessages.EMAIL_PLEASE_WRITE
import com.uliga.app.utils.ToastMessages.RELATIONSHIP_PLEASE_WRITE
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookUseCase
import com.uliga.domain.usecase.userAuth.remote.GetUserAuthDataExistedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AccountBookGenerationViewModel @Inject constructor(
    private val postAccountBookUseCase: PostAccountBookUseCase,
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val getUserAuthDataExistedUseCase: GetUserAuthDataExistedUseCase
) : ContainerHost<AccountBookGenerationUiState, AccountBookGenerationSideEffect>, BaseViewModel() {

    override val container =
        container<AccountBookGenerationUiState, AccountBookGenerationSideEffect>(
            AccountBookGenerationUiState.empty()
        )

    fun postAccountBook(
        name: String,
        relationship: String,
        categoryList: List<String>,
        emailList: List<String>
    ) = intent {
        launch {
            if (name.isEmpty()) {
                postSideEffect(
                    AccountBookGenerationSideEffect.ToastMessage(
                        ACCOUNT_BOOK_NAME_PLEASE_WRITE
                    )
                )
                updateIsLoading(false)
                return@launch
            }

            if (relationship.isEmpty()) {
                postSideEffect(
                    AccountBookGenerationSideEffect.ToastMessage(
                        RELATIONSHIP_PLEASE_WRITE
                    )
                )
                updateIsLoading(false)
                return@launch
            }

            val accountBookGenerationRequest = AccountBookGenerationRequest(
                name = name + "님의 가계부",
                categories = categoryList,
                emails = emailList,
                relationship = relationship
            )

            postAccountBookUseCase(accountBookGenerationRequest)
                .onSuccess {

                    getAccountBooksUseCase()
                        .onSuccess {
                            reduce {
                                state.copy(
                                    accountBooks = it
                                )
                            }

                            postSideEffect(AccountBookGenerationSideEffect.Finish)
                        }
                }
        }
    }

    fun getIsEmailExisted(email: String) = intent {

        launch {
            if (email.isEmpty()) {
                postSideEffect(AccountBookGenerationSideEffect.ToastMessage(EMAIL_PLEASE_WRITE))
                updateIsLoading(false)
                return@launch
            }

            getUserAuthDataExistedUseCase("mail", email)
                .onSuccess {

                    if (it.exists == null) {
                        updateIsLoading(false)
                        return@launch
                    }

                    if (it.exists!!) {
                        postSideEffect(AccountBookGenerationSideEffect.AddEmailChip)
                    } else {
                        postSideEffect(AccountBookGenerationSideEffect.ToastMessage(EMAIL_NO_EXISTED))
                    }
                }
        }
    }


    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AccountBookGenerationSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }

}