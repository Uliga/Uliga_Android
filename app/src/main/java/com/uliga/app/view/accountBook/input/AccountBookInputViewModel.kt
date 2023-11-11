package com.uliga.app.view.accountBook.input

import com.uliga.app.base.BaseViewModel
import com.uliga.app.view.schedule.ScheduleGenerationSideEffect
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import com.uliga.domain.usecase.accountbook.PostAccountBookTransactionUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookInputViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val postAccountBookTransactionUseCase: PostAccountBookTransactionUseCase
) : ContainerHost<AccountBookInputUiState, AccountBookInputSideEffect>, BaseViewModel() {

    override val container = container<AccountBookInputUiState, AccountBookInputSideEffect>(
        AccountBookInputUiState.empty()
    )

    init {
        initialize()
    }

    fun initialize() = intent {
        launch {
            fetchCurrentAccountBookInfo()
            fetchId()
        }
    }

    fun postAccountBookTransaction(
        transactionType: String,
        category: String,
        payment: String,
        date: String,
        account: String,
        value: Long,
        memo: String,
    ) = intent {
        launch {

            val currentAccountBookInfo = state.currentAccountInfo
            if (currentAccountBookInfo == null) {
                updateIsLoading(false)
                return@launch
            }

            val accountBookTransactionRequest = AccountBookTransactionRequest(
                id = currentAccountBookInfo.second,
                category = category,
                payment = payment,
                date = date,
                account = account,
                value = value,
                memo = memo,
                sharedAccountBook = listOf()
            )

            postAccountBookTransactionUseCase(transactionType, accountBookTransactionRequest)
                .onSuccess {
                    postSideEffect(AccountBookInputSideEffect.Finish)
                }
                .onFailure {
                    postSideEffect(AccountBookInputSideEffect.Finish)

                }
        }
    }

    private fun fetchCurrentAccountBookInfo() = intent {
        fetchCurrentAccountBookInfoUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountInfo = it
                    )
                }
            }.onFailure {
                postSideEffect(AccountBookInputSideEffect.ToastMessage("가계부 정보를 가져오는데 실패했습니다."))
            }
    }


    private fun fetchId() = intent {
        launch {
            fetchIdUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            id = it
                        )
                    }
                }
                .onFailure {
                    postSideEffect(AccountBookInputSideEffect.ToastMessage("사용자 정보를 가져오는데 실패했습니다."))
                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AccountBookInputSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }

}