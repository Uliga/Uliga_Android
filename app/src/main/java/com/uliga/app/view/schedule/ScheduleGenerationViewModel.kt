package com.uliga.app.view.schedule

import androidx.core.text.isDigitsOnly
import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.COST_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.DATE_INFO_PLEASE_CONFIRM
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_MODIFY_FAILURE
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_MODIFY_SUCCESS
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_REGISTER_FAILURE
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_REGISTER_SUCCESS
import com.uliga.app.utils.ToastMessages.MEMBER_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.SCHEDULE_NAME_PLEASE_CONFIRM
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignment
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.usecase.accountbook.PostFinanceScheduleToAccountBookUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.financeSchedule.PatchFinanceScheduleUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
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
    private val fetchIdUseCase: FetchIdUseCase,
    private val getMemberUseCase: GetMemberUseCase,
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase
) : ContainerHost<ScheduleGenerationUiState, ScheduleGenerationSideEffect>, BaseViewModel() {

    override val container = container<ScheduleGenerationUiState, ScheduleGenerationSideEffect>(
        ScheduleGenerationUiState.empty()
    )

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

                fetchId()
                getMember()
            }
        }

    }

    private fun fetchId() = intent {
        launch {
            fetchIdUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            id = it
                        )
                    }
                }
                .onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(MEMBER_INFO_GET_FAILURE))
                }
        }
    }

    private fun getMember() = intent {
        launch {
            getMemberUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            member = it
                        )
                    }
                }
                .onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(MEMBER_INFO_GET_FAILURE))
                }
        }
    }

    fun postFinanceScheduleToAccountBook(
        name: String,
        isIncome: Boolean,
        notificationDate: String,
        value: String,
    ) = intent {
        launch {

            val currentAccountBookId = state.accountBookId
            val userId = state.id


            if (currentAccountBookId == null || userId == null) {
                postSideEffect(
                    ScheduleGenerationSideEffect.ToastMessage(
                        ACCOUNT_BOOK_INFO_GET_FAILURE
                    )
                )
                updateIsLoading(false)
                return@launch
            }

            if (notificationDate.isEmpty() || notificationDate.toLong() > 31 || !notificationDate.isDigitsOnly()) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(DATE_INFO_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (name.isEmpty()) {
                postSideEffect(
                    ScheduleGenerationSideEffect.ToastMessage(
                        SCHEDULE_NAME_PLEASE_CONFIRM
                    )
                )
                updateIsLoading(false)
                return@launch
            }

            if (value.isEmpty() || !value.isDigitsOnly()) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(COST_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }


            val accountBookFinanceScheduleRequest = AccountBookFinanceScheduleRequest(
                id = currentAccountBookId,
                schedules = listOf(
                    AccountBookFinanceScheduleResult(
                        name = name,
                        isIncome = isIncome,
                        notificationDate = notificationDate.toLong(),
                        value = value.toLong(),
                        assignments = listOf(
                            AccountBookFinanceScheduleAssignment(
                                id = userId,
                                username = "",
                                value = value.toLong()
                            )
                        )
                    )
                )
            )

            postFinanceScheduleToAccountBookUseCase(accountBookFinanceScheduleRequest)
                .onSuccess {

                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(FINANCE_SCHEDULE_REGISTER_SUCCESS))
                    postSideEffect(ScheduleGenerationSideEffect.Finish)

                }.onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(FINANCE_SCHEDULE_REGISTER_FAILURE))
                }

        }
    }

    fun patchFinanceSchedule(
        scheduleId: Long,
        name: String,
        isIncome: Boolean,
        notificationDate: String,
        value: String,
    ) = intent {
        launch {
            val userId = state.id
            if (userId == null) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            if (notificationDate.toLong() > 31 || notificationDate.isEmpty() || !notificationDate.isDigitsOnly()) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(DATE_INFO_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (name.isEmpty()) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(SCHEDULE_NAME_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            if (value.isEmpty() || !value.isDigitsOnly()) {
                postSideEffect(ScheduleGenerationSideEffect.ToastMessage(COST_PLEASE_CONFIRM))
                updateIsLoading(false)
                return@launch
            }

            val financeScheduleUpdate = FinanceScheduleUpdate(
                id = scheduleId,
                name = name,
                isIncome = isIncome,
                notificationDate = notificationDate.toLong(),
                value = value.toLong(),
                assignments = hashMapOf<Long, Long>().apply {
                    this[userId] = value.toLong()
                }
            )

            patchFinanceScheduleUseCase(financeScheduleUpdate)
                .onSuccess {

                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(FINANCE_SCHEDULE_MODIFY_SUCCESS))
                    postSideEffect(ScheduleGenerationSideEffect.Finish)

                }
                .onFailure {
                    postSideEffect(ScheduleGenerationSideEffect.ToastMessage(FINANCE_SCHEDULE_MODIFY_FAILURE))

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