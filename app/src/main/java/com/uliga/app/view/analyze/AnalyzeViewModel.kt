package com.uliga.app.view.analyze

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookFixedRecordByMonthUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCategoryUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCompareUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val getAccountBookFixedRecordByMonthUseCase: GetAccountBookFixedRecordByMonthUseCase
) : ContainerHost<AnalyzeUiState, AnalyzeSideEffect>, BaseViewModel() {

    override val container = container<AnalyzeUiState, AnalyzeSideEffect>(AnalyzeUiState.empty())

    init {
        initialize()
    }

    fun initialize() = intent {
        launch {
            val currentDate = LocalDate.now()
            val currentYear = currentDate.year
            val currentMonth = currentDate.monthValue

            getAccountBookRecordByWeek(currentYear, currentMonth, 1)
            getAccountBookRecordByMonthForCategory(currentYear, currentMonth)
            getAccountBookRecordByMonthForCompare(currentYear, currentMonth)
            getAccountBookFixedRecordByMonth()
        }
    }

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

    private fun getAccountBookRecordByMonthForCompare(year: Int, month: Int) = intent {
        launch {
            val currentAccountBookInfo = state.currentAccountInfo ?: return@launch

            getAccountBookRecordByMonthForCompareUseCase(
                currentAccountBookInfo.second,
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
            val currentAccountBookInfo = state.currentAccountInfo ?: return@launch


            getAccountBookRecordByWeekUseCase(
                currentAccountBookInfo.second,
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
            val currentAccountBookInfo = state.currentAccountInfo ?: return@launch

            getAccountBookRecordByMonthForCategoryUseCase(
                currentAccountBookInfo.second,
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
            val currentAccountBookInfo = state.currentAccountInfo ?: return@launch

            getAccountBookFixedRecordByMonthUseCase(currentAccountBookInfo.second)
                .onSuccess {
                    reduce {
                        state.copy(
                            accountBookAnalyzeFixedRecordByMonth = it
                        )
                    }
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