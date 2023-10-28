package com.uliga.app.view.home

import com.uliga.domain.model.accountBook.analyze.byDay.AccountBookAnalyzeRecordByDay
import com.uliga.domain.model.accountBook.asset.AccountBookAsset
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.model.member.Member
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
    val member: Member? = null,
    val financeSchedules: FinanceSchedules? = null,
    val selectedSchedule: FinanceSchedule? = null,
    val currentMonthAccountBookAsset: AccountBookAsset? = null,
    val beforeMonthAccountBookAsset: AccountBookAsset? = null,
    val accountBookAnalyzeRecordByDay: AccountBookAnalyzeRecordByDay? = null
) {

    companion object {
        fun empty() = HomeUiState(
            isLoading = false,
            exception = null,
            id = null,
            member = null,
            currentAccountInfo = null,
            financeSchedules = null,
            currentMonthAccountBookAsset = null,
            accountBookAnalyzeRecordByDay = null
        )
    }
}