package com.uliga.app.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uliga.app.view.accountBook.generation.AccountBookGenerationSideEffect
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignment
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PostFinanceScheduleToAccountBookUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.financeSchedule.DeleteFinanceScheduleDetailUseCase
import com.uliga.domain.usecase.financeSchedule.GetFinanceScheduleDetailUseCase
import com.uliga.domain.usecase.financeSchedule.GetFinanceScheduleUseCase
import com.uliga.domain.usecase.financeSchedule.PatchFinanceScheduleUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val postFinanceScheduleToAccountBookUseCase: PostFinanceScheduleToAccountBookUseCase,
    private val getFinanceScheduleUseCase: GetFinanceScheduleUseCase,
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeUiState, HomeSideEffect>(HomeUiState.empty())


    init {
        getAccountBooks()
        getFinanceSchedule()
        fetchCurrentAccountBookInfo()
        fetchId()
    }

    fun getAccountBooks() = intent {

        getAccountBooksUseCase()
            .onSuccess {
                Log.d("getAccountBooks", it.toString())
            }
            .onFailure {

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

    fun getFinanceSchedule() = intent {
        getFinanceScheduleUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        financeSchedules = it
                    )
                }
            }
            .onFailure {

            }
    }


    fun postFinanceScheduleToAccountBook(
        name: String,
        isIncome: Boolean,
        notificationDate: Long,
        value: Long,
    ) =
        intent {

            val currentAccountBookInfo = state.currentAccountInfo ?: return@intent
            val userId = state.id ?: return@intent

            val accountBookFinanceScheduleRequest = AccountBookFinanceScheduleRequest(
                id = currentAccountBookInfo.second,
                schedules = listOf(
                    AccountBookFinanceScheduleResult(
                        name = name,
                        isIncome = isIncome,
                        notificationDate = notificationDate,
                        value = value,
                        assignments = listOf(
                            AccountBookFinanceScheduleAssignment(
                                id = userId,
                                username = "",
                                value = value
                            )
                        )
                    )
                )
            )

            postFinanceScheduleToAccountBookUseCase(accountBookFinanceScheduleRequest)
                .onSuccess {

                    getFinanceScheduleUseCase()
                        .onSuccess {
                            reduce {
                                state.copy(
                                    financeSchedules = it
                                )
                            }
                            postSideEffect(HomeSideEffect.FinishScheduleBottomSheet)
                        }
                        .onFailure {
                            postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))
                        }
                }
                .onFailure {
                    postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))

                }
        }

    override fun onCleared() {
        Log.d("homeViewModel", "onCleared")
        super.onCleared()
    }
}