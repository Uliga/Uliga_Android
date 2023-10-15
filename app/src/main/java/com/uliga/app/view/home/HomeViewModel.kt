package com.uliga.app.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.financeSchedule.AccountBookFinanceScheduleRequest
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleAssignment
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthAssetUseCase
import com.uliga.domain.usecase.accountbook.GetAccountBooksUseCase
import com.uliga.domain.usecase.accountbook.PatchAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookInvitationReplyUseCase
import com.uliga.domain.usecase.accountbook.PostFinanceScheduleToAccountBookUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookInfoUseCase
import com.uliga.domain.usecase.financeSchedule.DeleteFinanceScheduleDetailUseCase
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
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCurrentAccountBookInfoUseCase: FetchCurrentAccountBookInfoUseCase,
    private val fetchIdUseCase: FetchIdUseCase,
    private val getAccountBooksUseCase: GetAccountBooksUseCase,
    private val postFinanceScheduleToAccountBookUseCase: PostFinanceScheduleToAccountBookUseCase,
    private val getFinanceScheduleUseCase: GetFinanceScheduleUseCase,
    private val patchFinanceScheduleUseCase: PatchFinanceScheduleUseCase,
    private val deleteFinanceScheduleDetailUseCase: DeleteFinanceScheduleDetailUseCase,
    private val postAccountBookBudgetUseCase: PostAccountBookBudgetUseCase,
    private val patchAccountBookBudgetUseCase: PatchAccountBookBudgetUseCase,
    private val getAccountBookMonthAssetUseCase: GetAccountBookMonthAssetUseCase,
    private val getMemberUseCase: GetMemberUseCase,
    private val postAccountBookInvitationReplyUseCase: PostAccountBookInvitationReplyUseCase
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeUiState, HomeSideEffect>(HomeUiState.empty())


    init {
        getAccountBooks()
        getFinanceSchedule()
        fetchCurrentAccountBookInfo()
        fetchId()

        val currentDate = LocalDate.now()
        getAccountBookMonthAsset(true, currentDate.year, currentDate.monthValue)
        val beforeDate = currentDate.minusMonths(1)
        getAccountBookMonthAsset(false, beforeDate.year, beforeDate.monthValue)

        getMember()
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

    fun postAccountBookInvitationReply(
        id: Long,
        memberName: String,
        accountBookName: String,
        join: Boolean
    ) = intent {

        val accountBookInvitationReply = AccountBookInvitationReply(
            id = id,
            memberName = memberName,
            accountBookName = accountBookName,
            join = join
        )

        postAccountBookInvitationReplyUseCase(accountBookInvitationReply)
            .onSuccess {
                getMember()
            }
            .onFailure {

            }
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

    fun getAccountBookMonthAsset(isCurrent: Boolean, year: Int, month: Int) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        getAccountBookMonthAssetUseCase(
            accountBookId = currentAccountBookInfo.second,
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
        }.onFailure {

        }
    }

    fun updateFinanceSchedule(financeSchedule: FinanceSchedule?) = intent {
        reduce {
            state.copy(
                selectedSchedule = financeSchedule
            )
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

    fun patchFinanceSchedule(
        name: String,
        isIncome: Boolean,
        notificationDate: Long,
        value: Long,
    ) = intent {

        val selectedSchedule = state.selectedSchedule
        val userId = state.id ?: return@intent

        val financeScheduleUpdate = FinanceScheduleUpdate(
            id = selectedSchedule?.id ?: 0L,
            name = name,
            isIncome = isIncome,
            notificationDate = notificationDate,
            value = value,
            assignments = hashMapOf<Long, Long>().apply {
                this[userId] = selectedSchedule?.value ?: 0L
            }
        )

        patchFinanceScheduleUseCase(financeScheduleUpdate)
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

    fun deleteFinanceScheduleDetail(id: Long) = intent {
        deleteFinanceScheduleDetailUseCase(id)
            .onSuccess {
                getFinanceScheduleUseCase()
                    .onSuccess {
                        reduce {
                            state.copy(
                                financeSchedules = it
                            )
                        }
                    }
                    .onFailure {
                        postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))
                    }
            }
            .onFailure {
                postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))

            }
    }

    fun postAccountBookBudget(year: Long, month: Long, value: Long) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        val accountBookBudgetRequest = AccountBookBudgetRequest(
            id = currentAccountBookInfo.second,
            year = year,
            month = month,
            value = value,
            category = null
        )

        postAccountBookBudgetUseCase(accountBookBudgetRequest)
            .onSuccess {

                getAccountBookMonthAsset(true, year.toInt(), month.toInt())

                postSideEffect(
                    HomeSideEffect.FinishBudgetSettingBottomSheet
                )
            }.onFailure {

            }

    }

    fun patchAccountBookBudget(year: Long, month: Long, value: Long) = intent {
        val currentAccountBookInfo = state.currentAccountInfo ?: return@intent

        val accountBookBudgetRequest = AccountBookBudgetRequest(
            id = currentAccountBookInfo.second,
            year = year,
            month = month,
            value = value,
            category = null
        )

        patchAccountBookBudgetUseCase(accountBookBudgetRequest)
            .onSuccess {
                getAccountBookMonthAsset(true, year.toInt(), month.toInt())

                postSideEffect(
                    HomeSideEffect.FinishBudgetSettingBottomSheet
                )
            }.onFailure {

            }

    }


    override fun onCleared() {
        Log.d("homeViewModel", "onCleared")
        super.onCleared()
    }
}