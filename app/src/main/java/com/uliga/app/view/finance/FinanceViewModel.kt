package com.uliga.app.view.finance

import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.TRANSACTION_DELETE_SUCCESS
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthAssetUseCase
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthTransactionUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.accountbook.remote.DeleteAccountBookDayTransactionUseCase
import com.uliga.domain.usecase.accountbook.remote.GetAccountBookDayTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val getAccountBookMonthTransactionUseCase: GetAccountBookMonthTransactionUseCase,
    private val getAccountBookDayTransactionUseCase: GetAccountBookDayTransactionUseCase,
    private val getAccountBookMonthAssetUseCase: GetAccountBookMonthAssetUseCase,
    private val deleteAccountBookDayTransactionUseCase: DeleteAccountBookDayTransactionUseCase,
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase,
) : ContainerHost<FinanceUiState, FinanceSideEffect>, BaseViewModel() {

    override val container = container<FinanceUiState, FinanceSideEffect>(FinanceUiState.empty())

    init {
        initialize()
    }

    fun initialize() {
        observeCurrentAccountBookId()
    }

    private fun observeCurrentAccountBookId() = intent {
        launch {
            fetchCurrentAccountBookIdUseCase().collectLatest { accountBookId ->
                updateAccountBookId(accountBookId)
            }
        }
    }

    fun getAccountBookAsset() = intent {
        launch {
            val accountBookId = state.accountBookId
            if (accountBookId == null) {
                postSideEffect(FinanceSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            val currentDate = LocalDate.now()

            getAccountBookMonthAssetUseCase(
                accountBookId = accountBookId,
                month = currentDate.monthValue,
                year = currentDate.year
            )
        }
    }

    fun getAccountBookDayTransaction(year: Int, month: Int, day: Int) = intent {
        launch {
            val accountBookId = state.accountBookId
            if (accountBookId == null) {
                postSideEffect(FinanceSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookDayTransactionUseCase(accountBookId, year, month, day)
                .onSuccess {
                    reduce {
                        state.copy(
                            currentAccountBookAssetDay = it
                        )
                    }

                }
        }
    }

    fun getAccountBookMonthTransaction(year: Int, month: Int) = intent {
        launch {
            val accountBookId = state.accountBookId
            if (accountBookId == null) {
                postSideEffect(FinanceSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookMonthTransactionUseCase(accountBookId, year, month)
                .onSuccess {
                    reduce {
                        state.copy(
                            currentAccountBookAsset = it
                        )
                    }
                }
        }
    }

    fun getAccountBookTransaction(year: Int, month: Int, day: Int) = intent {
        launch {
            val accountBookId = state.accountBookId
            if (accountBookId == null) {
                postSideEffect(FinanceSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            val accountBookDayTransaction = async(Dispatchers.IO) {
                getAccountBookDayTransactionUseCase(
                    accountBookId,
                    year,
                    month,
                    day
                )
            }
            val accountBookMonthTransaction = async(Dispatchers.IO) {
                getAccountBookMonthTransactionUseCase(
                    accountBookId,
                    year,
                    month
                )
            }

            val currentAccountBookAssetDay = accountBookDayTransaction.await()
            val currentAccountBookAsset = accountBookMonthTransaction.await()

            reduce {
                state.copy(
                    currentAccountBookAssetDay = currentAccountBookAssetDay.getOrThrow(),
                    currentAccountBookAsset = currentAccountBookAsset.getOrThrow()
                )
            }
        }
    }

    fun deleteAccountBookDayTransaction(accountBookAssetItemId: Long) = intent {
        launch {
            deleteAccountBookDayTransactionUseCase(
                AccountBookTransactionIds(
                    listOf(
                        accountBookAssetItemId
                    )
                )
            ).onSuccess {
                postSideEffect(
                    FinanceSideEffect.DismissDeleteAlert
                )
                postSideEffect(
                    FinanceSideEffect.ToastMessage(TRANSACTION_DELETE_SUCCESS)
                )
            }
        }
    }

    suspend fun updateAccountBookId(accountBookId: Long?) = intent {
        launch {
            reduce {
                state.copy(
                    accountBookId = accountBookId
                )
            }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(FinanceSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }

}