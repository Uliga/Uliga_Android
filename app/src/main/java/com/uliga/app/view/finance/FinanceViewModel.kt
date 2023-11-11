package com.uliga.app.view.finance

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionIds
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthTransactionUseCase
import com.uliga.domain.usecase.accountbook.remote.DeleteAccountBookDayTransactionUseCase
import com.uliga.domain.usecase.accountbook.remote.GetAccountBookDayTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val getAccountBookMonthTransactionUseCase: GetAccountBookMonthTransactionUseCase,
    private val getAccountBookDayTransactionUseCase: GetAccountBookDayTransactionUseCase,
    private val deleteAccountBookDayTransactionUseCase: DeleteAccountBookDayTransactionUseCase
) : ContainerHost<FinanceUiState, FinanceSideEffect>, BaseViewModel() {

    override val container = container<FinanceUiState, FinanceSideEffect>(FinanceUiState.empty())

    fun initializeBaseInfo(id: Long?, currentAccountInfo: Pair<String, Long>?, member: Member?) =
        intent {
            launch {
                reduce {
                    state.copy(
                        id = id,
                        currentAccountInfo = currentAccountInfo,
                        member = member
                    )
                }
            }
        }

    fun getAccountBookDayTransaction(year: Int, month: Int, day: Int) = intent {
        launch {
            val currentAccountBookInfo = state.currentAccountInfo
            if (currentAccountBookInfo == null) {
                updateIsLoading(false)
                return@launch
            }

            getAccountBookDayTransactionUseCase(currentAccountBookInfo.second, year, month, day)
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
            val currentAccountBookInfo = state.currentAccountInfo
            if (currentAccountBookInfo == null) {
                updateIsLoading(false)
                return@launch
            }

            getAccountBookMonthTransactionUseCase(currentAccountBookInfo.second, year, month)
                .onSuccess {
                    reduce {
                        state.copy(
                            currentAccountBookAsset = it
                        )
                    }
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
                    FinanceSideEffect.ToastMessage("거래 내역을 삭제하는데 성공했습니다!")
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