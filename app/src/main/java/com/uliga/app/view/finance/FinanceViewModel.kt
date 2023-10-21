package com.uliga.app.view.finance

import androidx.lifecycle.ViewModel
import com.uliga.app.view.accountBook.input.AccountBookForInputSideEffect
import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetItem
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthTransactionUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookTransactionUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.accountbook.remote.DeleteAccountBookDayTransactionUseCase
import com.uliga.domain.usecase.accountbook.remote.GetAccountBookDayTransactionUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val getAccountBookMonthTransactionUseCase: GetAccountBookMonthTransactionUseCase,
    private val getAccountBookDayTransactionUseCase: GetAccountBookDayTransactionUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val postAccountBookTransactionUseCase: PostAccountBookTransactionUseCase,
    private val deleteAccountBookDayTransactionUseCase: DeleteAccountBookDayTransactionUseCase
) : ContainerHost<FinanceUiState, FinanceSideEffect>, ViewModel() {

    override val container = container<FinanceUiState, FinanceSideEffect>(FinanceUiState.empty())

    init {
        fetchCurrentAccountBookInfo()
        fetchId()
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

    fun postAccountBookTransaction(
        transactionType: String,
        category: String,
        payment: String,
        date: String,
        account: String,
        value: Long,
        memo: String
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
            sharedAccountBook = listOf(0)
        )


        postAccountBookTransactionUseCase(transactionType, accountBookTransactionRequest)
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountBookTransaction = it
                    )
                }

            }
            .onFailure {

            }
    }

    fun getAccountBookDayTransaction(year: Int, month: Int, day: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        getAccountBookDayTransactionUseCase(currentAccountBookInfo.second, year, month, day)
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountBookAssetDay = it
                    )
                }

            }
            .onFailure {

            }
    }

    fun getAccountBookMonthTransaction(year: Int, month: Int) = intent {

        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        getAccountBookMonthTransactionUseCase(currentAccountBookInfo.second, year, month)
            .onSuccess {
                reduce {
                    state.copy(
                        currentAccountBookAsset = it
                    )
                }
            }
            .onFailure {

            }
    }

    fun deleteAccountBookDayTransaction(accountBookAssetItemId: Long) = intent {

        deleteAccountBookDayTransactionUseCase(AccountBookTransactionIds(listOf(accountBookAssetItemId)))
            .onSuccess {
                postSideEffect(
                    FinanceSideEffect.DismissDeleteAlert
                )

            }
            .onFailure {

            }
    }

}