package com.uliga.app.view.analyze

import androidx.lifecycle.ViewModel
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookFixedRecordByMonthUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCategoryUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByMonthForCompareUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByWeekUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
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
) : ContainerHost<AnalyzeUiState, AnalyzeSideEffect>, ViewModel() {

    override val container = container<AnalyzeUiState, AnalyzeSideEffect>(AnalyzeUiState.empty())

    init {
        val currentDate = LocalDate.now()
        val currentYear = currentDate.year
        val currentMonth = currentDate.monthValue

        getAccountBookRecordByWeek(currentYear, currentMonth, 1)
        getAccountBookRecordByMonthForCategory(currentYear, currentMonth)
        getAccountBookRecordByMonthForCompare(currentYear, currentMonth)
        getAccountBookFixedRecordByMonth()
    }

    fun initializeBaseInfo(id: Long?, currentAccountInfo: Pair<String, Long>?, member: Member?) =
        intent {
            reduce { state.copy(isLoading = true) }

            reduce {
                state.copy(
                    id = id,
                    currentAccountInfo = currentAccountInfo,
                    member = member
                )
            }

            reduce { state.copy(isLoading = false) }

        }

    fun getAccountBookRecordByMonthForCompare(year: Int, month: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        reduce { state.copy(isLoading = true) }
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
        }.onFailure {

        }
        reduce { state.copy(isLoading = false) }

    }

    fun getAccountBookRecordByWeek(
        year: Int,
        month: Int,
        startDay: Int
    ) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        reduce { state.copy(isLoading = true) }

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
        }.onFailure {


        }

        reduce { state.copy(isLoading = false) }

    }

    fun getAccountBookRecordByMonthForCategory(year: Int, month: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        reduce { state.copy(isLoading = true) }

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
        }.onFailure {

        }
        reduce { state.copy(isLoading = false) }

    }

    fun getAccountBookFixedRecordByMonth() = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        reduce { state.copy(isLoading = true) }

        getAccountBookFixedRecordByMonthUseCase(currentAccountBookInfo.second)
            .onSuccess {
                reduce {
                    state.copy(
                        accountBookAnalyzeFixedRecordByMonth = it
                    )
                }
            }
            .onFailure {

            }

        reduce { state.copy(isLoading = false) }

    }

}