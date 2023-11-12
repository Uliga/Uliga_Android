package com.uliga.app.view.accountBook.generation

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.accountBook.AccountBookGenerationRequest
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookUseCase
import com.uliga.domain.usecase.userAuth.GetUserAuthDataExistedUseCase
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
                postSideEffect(AccountBookGenerationSideEffect.ToastMessage("가계부 이름을 입력해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if (relationship.isEmpty()) {
                postSideEffect(AccountBookGenerationSideEffect.ToastMessage("관계를 입력해주세요."))
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
                postSideEffect(AccountBookGenerationSideEffect.ToastMessage("이메일을 적어주세요."))
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
                        postSideEffect(AccountBookGenerationSideEffect.ToastMessage("이메일이 존재하지 않습니다."))
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