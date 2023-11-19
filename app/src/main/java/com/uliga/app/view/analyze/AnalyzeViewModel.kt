package com.uliga.app.view.analyze

import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.accountbook.remote.GetCurrentAccountBookAssetUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookFixedRecordByMonthUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCategoryUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCompareUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AnalyzeViewModel @Inject constructor(
    private val getAccountBookRecordByMonthForCategoryUseCase: GetAccountBookRecordByMonthForCategoryUseCase,
    private val getAccountBookRecordByMonthForCompareUseCase: GetAccountBookRecordByMonthForCompareUseCase,
    private val getAccountBookRecordByWeekUseCase: GetAccountBookRecordByWeekUseCase,
    private val getAccountBookFixedRecordByMonthUseCase: GetAccountBookFixedRecordByMonthUseCase,
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase,
    private val getCurrentAccountBookAssetUseCase: GetCurrentAccountBookAssetUseCase
) : ContainerHost<AnalyzeUiState, AnalyzeSideEffect>, BaseViewModel() {

    override val container = container<AnalyzeUiState, AnalyzeSideEffect>(AnalyzeUiState.empty())

    init {
        initialize()
    }

    fun initialize() {
        observeCurrentAccountBookId()
    }

    fun observeCurrentAccountBookId() = intent {
        launch {
            fetchCurrentAccountBookIdUseCase().collectLatest { accountBookId ->
                updateAccountBookId(accountBookId)

                if (accountBookId == null) {
                    postSideEffect(AnalyzeSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                    updateIsLoading(false)
                    return@collectLatest
                }

                observeCurrentAccountBookAsset()
            }
        }
    }

    fun observeCurrentAccountBookAsset() = intent {
        launch {
            getCurrentAccountBookAssetUseCase().collectLatest {

                val currentDate = LocalDate.now()
                val currentYear = currentDate.year
                val currentMonth = currentDate.monthValue

                getAccountBookRecordByWeek(currentYear, currentMonth, 1)
                getAccountBookRecordByMonthForCategory(currentYear, currentMonth)
                getAccountBookRecordByMonthForCompare(currentYear, currentMonth)
            }
        }
    }

    private fun getAccountBookRecordByMonthForCompare(year: Int, month: Int) = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(AnalyzeSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookRecordByMonthForCompareUseCase(
                currentAccountBookId,
                year,
                month
            ).onSuccess {
                reduce {
                    state.copy(
                        accountBookAnalyzeByMonthForCompare = it
                    )
                }
            }
        }
    }

    private fun getAccountBookRecordByWeek(
        year: Int,
        month: Int,
        startDay: Int
    ) = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(AnalyzeSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookRecordByWeekUseCase(
                currentAccountBookId,
                year,
                month,
                startDay
            ).onSuccess {
                reduce {
                    state.copy(
                        accountBookAnalyzeRecordByWeek = it
                    )
                }
            }
        }
    }

    private fun getAccountBookRecordByMonthForCategory(year: Int, month: Int) = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(AnalyzeSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookRecordByMonthForCategoryUseCase(
                currentAccountBookId,
                year,
                month
            ).onSuccess {
                reduce {
                    state.copy(
                        accountBookAnalyzeRecordByMonthForCategory = it
                    )
                }
            }
        }
    }

    private fun getAccountBookFixedRecordByMonth() = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(AnalyzeSideEffect.ToastMessage(ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            getAccountBookFixedRecordByMonthUseCase(currentAccountBookId)
                .onSuccess {
                    reduce {
                        state.copy(
                            accountBookAnalyzeFixedRecordByMonth = it
                        )
                    }
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
        postSideEffect(AnalyzeSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}