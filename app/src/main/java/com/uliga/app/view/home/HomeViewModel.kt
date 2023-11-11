package com.uliga.app.view.home

import androidx.core.text.isDigitsOnly
import com.uliga.app.base.BaseViewModel
import com.uliga.app.view.accountBook.input.AccountBookInputSideEffect
import com.uliga.domain.model.accountBook.budget.AccountBookBudgetRequest
import com.uliga.domain.model.accountBook.invitation.AccountBookInvitationReply
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.member.Member
import com.uliga.domain.usecase.accountbook.GetAccountBookMonthAssetUseCase
import com.uliga.domain.usecase.accountbook.PatchAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookBudgetUseCase
import com.uliga.domain.usecase.accountbook.PostAccountBookInvitationReplyUseCase
import com.uliga.domain.usecase.accountbook.remote.analyze.GetAccountBookRecordByDayUseCase
import com.uliga.domain.usecase.financeSchedule.DeleteFinanceScheduleDetailUseCase
import com.uliga.domain.usecase.financeSchedule.GetFinanceScheduleUseCase
import com.uliga.domain.usecase.member.GetMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
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
    private val getAccountBookAnalyzeRecordByDayUseCase: GetAccountBookRecordByDayUseCase
) : ContainerHost<HomeUiState, HomeSideEffect>, BaseViewModel() {

    override val container = container<HomeUiState, HomeSideEffect>(HomeUiState.empty())


    init {
        initialize()
    }

    fun initialize() = intent {
        launch {
            val currentDate = LocalDate.now()
            getAccountBookMonthAsset(true, currentDate.year, currentDate.monthValue)
            val beforeDate = currentDate.minusMonths(1)
            getAccountBookMonthAsset(false, beforeDate.year, beforeDate.monthValue)

            getAccountBookAnalyzeRecordByDay(currentDate.year, currentDate.monthValue)

            getFinanceSchedule()
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
        }
    }

    private fun getAccountBookAnalyzeRecordByDay(year: Int, month: Int) = intent {

        launch {
            val currentAccountBookInfo = state.currentAccountInfo
            if(currentAccountBookInfo == null) {
                postSideEffect(HomeSideEffect.ToastMessage("사용자 정보를 가져오는데 실패했습니다."))
                updateIsLoading(false)
                return@launch
            }
            getAccountBookAnalyzeRecordByDayUseCase(currentAccountBookInfo.second, year, month)
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
                        HomeSideEffect.ToastMessage("가계부에 합류하였습니다!")
                    )
                }
        }
    }

    private fun getAccountBookMonthAsset(isCurrent: Boolean, year: Int, month: Int) = intent {
        launch {

            val currentAccountBookInfo = state.currentAccountInfo
            if(currentAccountBookInfo == null) {
                postSideEffect(HomeSideEffect.ToastMessage("사용자 정보를 가져오는데 실패했습니다."))
                updateIsLoading(false)
                return@launch
            }
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
                            postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 삭제하는데 성공했습니다."))

                        }
                        .onFailure {
                            postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))
                        }
                }
                .onFailure {
                    postSideEffect(HomeSideEffect.ToastMessage("금융 일정을 등록하는데 실패했습니다."))
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
            val currentAccountBookInfo = state.currentAccountInfo
            if(currentAccountBookInfo == null) {
                postSideEffect(HomeSideEffect.ToastMessage("사용자 정보를 가져오는데 실패했습니다."))
                updateIsLoading(false)
                return@launch
            }

            if(value.isEmpty()) {
                postSideEffect(HomeSideEffect.ToastMessage("예산을 입력해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if(!value.isDigitsOnly() || value.toLong() <= 0L) {
                postSideEffect(HomeSideEffect.ToastMessage("예산을 다시 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            val accountBookBudgetRequest = AccountBookBudgetRequest(
                id = currentAccountBookInfo.second,
                year = year,
                month = month,
                value = value.toLong(),
                category = null
            )

            postAccountBookBudgetUseCase(accountBookBudgetRequest)
                .onSuccess {

                    runBlocking {
                        getAccountBookMonthAsset(true, year.toInt(), month.toInt())
                    }
                    postSideEffect(HomeSideEffect.ToastMessage("예산을 등록하는데 성공했습니다."))
                    postSideEffect(HomeSideEffect.FinishBudgetSettingBottomSheet)
                }
        }
    }

    fun patchAccountBookBudget(year: Long, month: Long, value: String) = intent {
        launch {
            val currentAccountBookInfo = state.currentAccountInfo
            if(currentAccountBookInfo == null) {
                postSideEffect(HomeSideEffect.ToastMessage("사용자 정보를 가져오는데 실패했습니다."))
                updateIsLoading(false)
                return@launch
            }

            if(value.isEmpty()) {
                postSideEffect(HomeSideEffect.ToastMessage("예산을 입력해주세요."))
                updateIsLoading(false)
                return@launch
            }

            if(!value.isDigitsOnly() || value.toLong() <= 0L) {
                postSideEffect(HomeSideEffect.ToastMessage("예산을 다시 확인해주세요."))
                updateIsLoading(false)
                return@launch
            }

            val accountBookBudgetRequest = AccountBookBudgetRequest(
                id = currentAccountBookInfo.second,
                year = year,
                month = month,
                value = value.toLong(),
                category = null
            )

            patchAccountBookBudgetUseCase(accountBookBudgetRequest)
                .onSuccess {

                    runBlocking {
                        getAccountBookMonthAsset(true, year.toInt(), month.toInt())
                    }
                    postSideEffect(HomeSideEffect.ToastMessage("예산을 수정하는데 성공했습니다."))
                    postSideEffect(
                        HomeSideEffect.FinishBudgetSettingBottomSheet
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