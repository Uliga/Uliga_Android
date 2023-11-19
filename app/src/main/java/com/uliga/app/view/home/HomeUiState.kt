package com.uliga.app.view.home

import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDay
import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonth
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.model.member.Member
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val accountBookId: Long? = null,
    val accountBookName: String? = null,
    val member: Member? = null,
    val financeSchedules: FinanceSchedules? = null,
    val selectedSchedule: FinanceSchedule? = null,
    val currentMonthAccountBookAsset: AccountBookAsset? = null,
    val beforeMonthAccountBookAsset: AccountBookAsset? = null,
    val accountBookAnalyzeRecordByDay: AccountBookAnalyzeRecordByDay? = null,
    val accountBookAnalyzeFixedRecordByMonth: AccountBookAnalyzeFixedRecordByMonth? = null
) {

    companion object {
        fun empty() = HomeUiState(
            isLoading = false,
            id = null,
            member = null,
            accountBookId = null,
            accountBookName = null,
            financeSchedules = null,
            currentMonthAccountBookAsset = null,
            accountBookAnalyzeRecordByDay = null,
            accountBookAnalyzeFixedRecordByMonth = null
        )
    }
}