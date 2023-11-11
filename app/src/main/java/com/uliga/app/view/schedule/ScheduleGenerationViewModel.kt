package com.uliga.app.view.schedule

import com.uliga.app.base.BaseViewModel
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignment
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.accountbook.PostFinanceScheduleToAccountBookUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.financeSchedule.GetFinanceScheduleUseCase
import com.uliga.domain.usecase.financeSchedule.PatchFinanceScheduleUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class ScheduleGenerationViewModel @Inject constructor(
    private val patchFinanceScheduleUseCase: PatchFinanceScheduleUseCase,
    private val postFinanceScheduleToAccountBookUseCase: PostFinanceScheduleToAccountBookUseCase,
    private val getFinanceScheduleUseCase: GetFinanceScheduleUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val getMemberUseCase: GetMemberUseCase
    ) : ContainerHost<ScheduleGenerationUiState, ScheduleGenerationSideEffect>, BaseViewModel() {

    override val container = container<ScheduleGenerationUiState, ScheduleGenerationSideEffect>(
        ScheduleGenerationUiState.empty()
    )

    init {
        fetchCurrentAccountBookInfo()
        fetchId()
        getMember()
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

    fun getMember() = intent {
        getMemberUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        member = it
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
    ) = intent {
        launch {

            val currentAccountBookInfo = state.currentAccountInfo
            val userId = state.id

            if (currentAccountBookInfo == null || userId == null) {
                updateIsLoading(false)
                return@launch
            }

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

                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage("금융 일정을 등록하는데 성공했습니다."))
                    postSideEffect(ScheduleGenerationSideEffect.Finish)

                }.onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))
                }

        }
    }

    fun patchFinanceSchedule(
        scheduleId: Long,
        name: String,
        isIncome: Boolean,
        notificationDate: Long,
        value: Long,
    ) = intent {
        launch {
            val userId = state.id
            if (userId == null) {
                updateIsLoading(false)
                return@launch
            }

            val financeScheduleUpdate = FinanceScheduleUpdate(
                id = scheduleId,
                name = name,
                isIncome = isIncome,
                notificationDate = notificationDate,
                value = value,
                assignments = hashMapOf<Long, Long>().apply {
                    this[userId] = value
                }
            )

            patchFinanceScheduleUseCase(financeScheduleUpdate)
                .onSuccess {

                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage("금융 일정을 수정하는데 성공했습니다."))
                    postSideEffect(ScheduleGenerationSideEffect.Finish)

                }
                .onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))

                }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(ScheduleGenerationSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}