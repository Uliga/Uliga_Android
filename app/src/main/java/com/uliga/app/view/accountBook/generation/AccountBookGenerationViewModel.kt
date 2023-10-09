package com.uliga.app.view.accountBook.generation

import androidx.lifecycle.ViewModel
import com.uliga.app.view.auth.AuthSideEffect
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
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
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val deleteMemberUseCase: DeleteMemberUseCase,
    private val getUserAuthDataExistedUseCase: GetUserAuthDataExistedUseCase
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

    fun getIsEmailExisted(email: String) = intent {
        if(email.isEmpty()) {
            postSideEffect(AccountBookGenerationSideEffect.ToastMessage("닉네임을 적어주세요."))
            return@intent
        }

        getUserAuthDataExistedUseCase("mail", email)
            .onSuccess {

                if(it.exists == null) return@intent

                reduce {
                    state.copy(
                        isLoading = true,
                    )
                }

                if(it.exists!!) {
                    postSideEffect(AccountBookGenerationSideEffect.AddEmailChip)
                } else {
                    postSideEffect(AccountBookGenerationSideEffect.ToastMessage("이메일이 존재하지 않습니다."))
                }

                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
            }.onFailure {
                reduce {
                    state.copy(
                        isLoading = false,
                    )
                }
            }
    }


}