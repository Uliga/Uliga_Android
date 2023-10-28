package com.uliga.app.view.accountBook.generation

import androidx.lifecycle.ViewModel
import com.uliga.domain.model.accountBook.AccountBook
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
class AccountBookGenerationViewModel @Inject constructor(
    private val postAccountBookUseCase: PostAccountBookUseCase,
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val getUserAuthDataExistedUseCase: GetUserAuthDataExistedUseCase,
    private val updateAccountBookUseCase: UpdateAccountBookUseCase
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
        reduce { state.copy(isLoading = true) }

        getAccountBooksUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        accountBooks = it
                    )
                }

            }
            .onFailure {

            }

        reduce { state.copy(isLoading = false) }

    }

    fun getIsEmailExisted(email: String) = intent {
        if (email.isEmpty()) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("닉네임을 적어주세요."))
            return@intent
        }
        reduce { state.copy(isLoading = true) }

        getUserAuthDataExistedUseCase("mail", email)
            .onSuccess {

                if (it.exists == null) return@intent

                if (it.exists!!) {
                    postSideEffect(AccountBookGenerationSideEffect.AddEmailChip)
                } else {
                    postSideEffect(AccountBookGenerationSideEffect.ToastMessage("이메일이 존재하지 않습니다."))
                }


            }.onFailure {
            }

        reduce {
            state.copy(
                isLoading = false,
            )
        }
    }

    fun postAccountBook(
        name: String,
        relationship: String,
        categoryList: List<String>,
        emailList: List<String>
    ) = intent {
        if (name.isEmpty()) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("가계부 이름을 입력해주세요."))
            return@intent
        }

        if (relationship.isEmpty()) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("관계를 입력해주세요."))
            return@intent
        }

        reduce { state.copy(isLoading = true) }

        val accountBookGenerationRequest = AccountBookGenerationRequest(
            name = name,
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

                        postSideEffect(AccountBookGenerationSideEffect.FinishAccountBookGenerationBottomSheet)

                    }
                    .onFailure {

                    }
            }
            .onFailure {

            }

        reduce { state.copy(isLoading = false) }

    }

    fun updateAccountBook(selectedIndex: Int, accountBooks: AccountBooks?) = intent {

        if(selectedIndex == -1) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("가계부를 선택해주세요."))
            return@intent
        }

        if(accountBooks == null) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("가계부를 찾을 수 없습니다."))
            return@intent
        }

        reduce { state.copy(isLoading = true) }


        val accountBookName = accountBooks.accountBooks[selectedIndex].info.accountBookName
        val accountBookId = accountBooks.accountBooks[selectedIndex].info.accountBookId

        updateAccountBookUseCase(accountBookName, accountBookId)
            .onSuccess {
                postSideEffect(AccountBookGenerationSideEffect.NavigateToMainActivity)
            }
            .onFailure {

            }
        reduce { state.copy(isLoading = false) }


    }


}