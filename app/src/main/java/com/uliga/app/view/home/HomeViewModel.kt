package com.uliga.app.view.home

import android.util.Log
import androidx.core.text.isDigitsOnly
import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_JOIN_SUCCESS
import com.uliga.app.utils.ToastMessages.BUDGET_INPUT_CHECK_AGAIN
import com.uliga.app.utils.ToastMessages.BUDGET_INPUT_MODIFY_SUCCESS
import com.uliga.app.utils.ToastMessages.BUDGET_INPUT_RETRY
import com.uliga.app.utils.ToastMessages.BUDGET_REGISTER_SUCCESS
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_DELETE_FAILURE
import com.uliga.app.utils.ToastMessages.FINANCE_SCHEDULE_DELETE_SUCCESS
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthAssetUseCase
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PatchAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookInvitationReplyUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookNameUseCase
import com.uliga.domain.usecase.accountbook.local.UpdateAccountBookUseCase
import com.uliga.domain.usecase.accountbook.remote.GetCurrentAccountBookAssetUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookFixedRecordByMonthUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByDayUseCase
import com.uliga.domain.usecase.financeSchedule.DeleteFinanceScheduleDetailUseCase
import com.uliga.domain.usecase.financeSchedule.GetFinanceScheduleUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
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
class HomeViewModel @Inject constructor(
    private val getFinanceScheduleUseCase: GetFinanceScheduleUseCase,
    private val deleteFinanceScheduleDetailUseCase: DeleteFinanceScheduleDetailUseCase,
    private val postAccountBookBudgetUseCase: PostAccountBookBudgetUseCase,
    private val patchAccountBookBudgetUseCase: PatchAccountBookBudgetUseCase,
    private val getAccountBookMonthAssetUseCase: GetAccountBookMonthAssetUseCase,
    private val getMemberUseCase: GetMemberUseCase,
    private val postAccountBookInvitationReplyUseCase: PostAccountBookInvitationReplyUseCase,
    private val getAccountBookAnalyzeRecordByDayUseCase: GetAccountBookRecordByDayUseCase,
    private val getAccountBookFixedRecordByMonthUseCase: GetAccountBookFixedRecordByMonthUseCase,
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase,
    private val fetchCurrentAccountBookNameUseCase: FetchCurrentAccountBookNameUseCase,
    private val getCurrentAccountBookAssetUseCase: GetCurrentAccountBookAssetUseCase,
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val updateAccountBookUseCase: UpdateAccountBookUseCase
) : ContainerHost<HomeUiState, HomeSideEffect>, BaseViewModel() {

    override val container = container<HomeUiState, HomeSideEffect>(HomeUiState.empty())

    init {
        initialize()
    }

    fun initialize() {
        getFinanceSchedule()

        observeCurrentAccountBookId()

        observeCurrentAccountBookName()
    }

    fun observeCurrentAccountBookId() = intent {
        launch {
            fetchCurrentAccountBookIdUseCase().collectLatest { accountBookId ->
                updateAccountBookId(accountBookId)

                getMember()

                if (accountBookId == null) {
                    postSideEffect(HomeSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                    updateIsLoading(false)
                    return@collectLatest
                }

                val currentDate = LocalDate.now()
                val beforeDate = currentDate.minusMonths(1)
                getAccountBookMonthAsset(
                    false,
                    beforeDate.year,
                    beforeDate.monthValue,
                    accountBookId
                )

                getAccountBookAnalyzeRecordByDay(
                    currentDate.year,
                    currentDate.monthValue,
                    accountBookId
                )

                getAccountBookFixedRecordByMonth()

                observeCurrentAccountBookAsset()
            }
        }
    }

    private fun observeCurrentAccountBookAsset() = intent {
        launch {
            getCurrentAccountBookAssetUseCase().collectLatest {
                updateCurrentAccountBookAsset(it)
            }
        }
    }
    private fun observeCurrentAccountBookName() = intent {
        launch {
            fetchCurrentAccountBookNameUseCase().collectLatest { accountBookName ->
                if(accountBookName.isNullOrEmpty() || accountBookName.isBlank()) {
                    getAccountBooks()
                } else {
                    updateAccountBookName(accountBookName)
                }
            }
        }
    }

    private suspend fun getAccountBooks() = intent {
        launch {
            getAccountBooksUseCase()
                .onSuccess {
                    val accountBookName = it.accountBooks[0].info.accountBookName
                    val accountBookId = it.accountBooks[0].info.accountBookId

                    updateAccountBookUseCase(accountBookName, accountBookId)
                        .onSuccess {
                            updateAccountBookName(accountBookName)
                        }
                }
        }
    }

    private suspend fun getMember() = intent {
        launch {
            getMemberUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            member = it
                        )
                    }
                }
        }
    }

    private suspend fun getAccountBookMonthAsset(
        isCurrent: Boolean,
        year: Int,
        month: Int,
        accountBookId: Long
    ) = intent {
        launch {
            getAccountBookMonthAssetUseCase(
                isCurrent = isCurrent,
                accountBookId = accountBookId,
                year = year,
                month = month
            ).onSuccess {
                reduce {
                    if (isCurrent) {
                        state.copy(
                            currentMonthAccountBookAsset = it
                        )
                    } else {
                        state.copy(
                            beforeMonthAccountBookAsset = it
                        )
                    }
                }
            }
        }
    }

    private suspend fun getAccountBookAnalyzeRecordByDay(
        year: Int,
        month: Int,
        accountBookId: Long
    ) =
        intent {
            launch {
                getAccountBookAnalyzeRecordByDayUseCase(accountBookId, year, month)
                    .onSuccess {
                        reduce {
                            state.copy(
                                accountBookAnalyzeRecordByDay = it
                            )
                        }
                    }
            }
        }

    fun postAccountBookInvitationReply(
        id: Long,
        memberName: String,
        accountBookName: String,
        join: Boolean
    ) = intent {
        launch {
            val accountBookInvitationReply = AccountBookInvitationReply(
                id = id,
                memberName = memberName,
                accountBookName = accountBookName,
                join = join
            )

            postAccountBookInvitationReplyUseCase(accountBookInvitationReply)
                .onSuccess {
                    getMember()

                    postSideEffect(
                        HomeSideEffect.ToastMessage(ACCOUNT_BOOK_JOIN_SUCCESS)
                    )
                }
        }
    }

    fun updateFinanceSchedule(financeSchedule: FinanceSchedule?) = intent {
        launch {
            reduce {
                state.copy(
                    selectedSchedule = financeSchedule
                )
            }
        }
    }

    fun deleteFinanceScheduleDetail(id: Long) = intent {
        launch {
            deleteFinanceScheduleDetailUseCase(id)
                .onSuccess {

                    getFinanceScheduleUseCase()
                        .onSuccess {
                            reduce {
                                state.copy(
                                    financeSchedules = it
                                )
                            }
                            postSideEffect(
                                HomeSideEffect.ToastMessage(
                                    FINANCE_SCHEDULE_DELETE_SUCCESS
                                )
                            )

                            getAccountBookFixedRecordByMonth()
                        }
                        .onFailure {
                            postSideEffect(
                                HomeSideEffect.ToastMessage(
                                    FINANCE_SCHEDULE_DELETE_FAILURE
                                )
                            )
                        }
                }
                .onFailure {
                    postSideEffect(HomeSideEffect.ToastMessage(FINANCE_SCHEDULE_DELETE_FAILURE))
                }
        }
    }

    fun getFinanceSchedule() = intent {
        launch {
            getFinanceScheduleUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            financeSchedules = it
                        )
                    }
                }
        }
    }

    fun postAccountBookBudget(year: Long, month: Long, value: String) = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(HomeSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            if (value.isEmpty()) {
                postSideEffect(HomeSideEffect.ToastMessage(BUDGET_INPUT_RETRY))
                updateIsLoading(false)
                return@launch
            }

            if (!value.isDigitsOnly() || value.toLong() <= 0L) {
                postSideEffect(HomeSideEffect.ToastMessage(BUDGET_INPUT_CHECK_AGAIN))
                updateIsLoading(false)
                return@launch
            }

            val accountBookBudgetRequest = AccountBookBudgetRequest(
                id = currentAccountBookId,
                year = year,
                month = month,
                value = value.toLong(),
                category = null
            )

            postAccountBookBudgetUseCase(accountBookBudgetRequest)
                .onSuccess {

                    getAccountBookMonthAsset(
                        true,
                        year.toInt(),
                        month.toInt(),
                        currentAccountBookId
                    )

                    postSideEffect(HomeSideEffect.ToastMessage(BUDGET_REGISTER_SUCCESS))
                    postSideEffect(HomeSideEffect.FinishBudgetSettingBottomSheet)
                }
        }
    }

    fun patchAccountBookBudget(year: Long, month: Long, value: String) = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(HomeSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            if (value.isEmpty()) {
                postSideEffect(HomeSideEffect.ToastMessage(BUDGET_INPUT_RETRY))
                updateIsLoading(false)
                return@launch
            }

            if (!value.isDigitsOnly() || value.toLong() <= 0L) {
                postSideEffect(HomeSideEffect.ToastMessage(BUDGET_INPUT_CHECK_AGAIN))
                updateIsLoading(false)
                return@launch
            }

            val accountBookBudgetRequest = AccountBookBudgetRequest(
                id = currentAccountBookId,
                year = year,
                month = month,
                value = value.toLong(),
                category = null
            )

            patchAccountBookBudgetUseCase(accountBookBudgetRequest)
                .onSuccess {

                    getAccountBookMonthAsset(
                        true,
                        year.toInt(),
                        month.toInt(),
                        currentAccountBookId
                    )

                    postSideEffect(HomeSideEffect.ToastMessage(BUDGET_INPUT_MODIFY_SUCCESS))
                    postSideEffect(
                        HomeSideEffect.FinishBudgetSettingBottomSheet
                    )
                }
        }
    }

    fun getAccountBookFixedRecordByMonth() = intent {
        launch {
            val currentAccountBookId = state.accountBookId
            if (currentAccountBookId == null) {
                postSideEffect(HomeSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
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

    private suspend fun updateAccountBookId(accountBookId: Long?) = intent {
        launch {
            reduce {
                state.copy(
                    accountBookId = accountBookId
                )
            }
        }
    }

    private suspend fun updateAccountBookName(accountBookName: String?) = intent {
        launch {
            reduce {
                state.copy(
                    accountBookName = accountBookName
                )
            }
        }
    }

    private suspend fun updateCurrentAccountBookAsset(accountBookAsset: AccountBookAsset?) = intent {
        launch {
            reduce {
                state.copy(
                    currentMonthAccountBookAsset = accountBookAsset
                )
            }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(HomeSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }
}