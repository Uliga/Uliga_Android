package com.uliga.app.view.accountBook.input

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import com.uliga.domain.usecase.accountbook.PostAccountBookTransactionUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookForInputViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val postAccountBookTransactionUseCase: PostAccountBookTransactionUseCase
) : ContainerHost<AccountBookForInputUiState, AccountBookForInputSideEffect>, BaseViewModel() {

    override val container = container<AccountBookForInputUiState, AccountBookForInputSideEffect>(
        AccountBookForInputUiState.empty()
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
                    postSideEffect(AccountBookForInputSideEffect.Finish)
                }
                .onFailure {
                    postSideEffect(AccountBookForInputSideEffect.Finish)

                }
        }
    }

    fun fetchCurrentAccountBookInfo() = intent {
        launch {
            fetchCurrentAccountBookInfoUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            currentAccountInfo = it
                        )
                    }
                }
        }
    }

    fun fetchId() = intent {
        launch {
            fetchIdUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            id = it
                        )
                    }
                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AccountBookForInputSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }

}