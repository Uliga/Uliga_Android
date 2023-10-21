package com.uliga.app.view.accountBook.input

import androidx.lifecycle.ViewModel
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
) : ContainerHost<AccountBookForInputUiState, AccountBookForInputSideEffect>, ViewModel() {

    override val container = container<AccountBookForInputUiState, AccountBookForInputSideEffect>(
        AccountBookForInputUiState.empty()
    )

    init {
        fetchCurrentAccountBookInfo()
        fetchId()
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
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

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

    fun fetchCurrentAccountBookInfo() = intent {
        fetchCurrentAccountBookInfoUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountInfo = it
                    )
                }
            }
            .onFailure {

            }
    }

    fun fetchId() = intent {
        fetchIdUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        id = it
                    )
                }
            }
            .onFailure {

            }
    }

}