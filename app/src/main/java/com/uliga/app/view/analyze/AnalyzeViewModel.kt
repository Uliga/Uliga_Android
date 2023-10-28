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
    private val fetchIdUseCase: FetchIdUseCase,
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val getAccountBookRecordByMonthForCategoryUseCase: GetAccountBookRecordByMonthForCategoryUseCase,
    private val getAccountBookRecordByMonthForCompareUseCase: GetAccountBookRecordByMonthForCompareUseCase,
    private val getAccountBookRecordByWeekUseCase: GetAccountBookRecordByWeekUseCase,
    private val getAccountBookFixedRecordByMonthUseCase: GetAccountBookFixedRecordByMonthUseCase
) : ContainerHost<AnalyzeUiState, AnalyzeSideEffect>, ViewModel() {

    override val container = container<AnalyzeUiState, AnalyzeSideEffect>(AnalyzeUiState.empty())

    init {
        fetchCurrentAccountBookInfo()
        fetchId()

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
            reduce {
                state.copy(
                    id = id,
                    currentAccountInfo = currentAccountInfo,
                    member = member
                )
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

    fun getAccountBookRecordByMonthForCompare(year: Int, month: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

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
    }

    fun getAccountBookRecordByWeek(
        year: Int,
        month: Int,
        startDay: Int
    ) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

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
    }

    fun getAccountBookRecordByMonthForCategory(year: Int, month: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

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
    }

    fun getAccountBookFixedRecordByMonth() = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

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
    }

}