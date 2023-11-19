package com.uliga.app.view.analyze

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.accountBook.analyze.byMonth.category.AccountBookAnalyzeRecordByMonthForCategory
import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare
import com.uliga.domain.model.accountBook.analyze.byMonth.schedule.AccountBookAnalyzeFixedRecordByMonth
import com.uliga.domain.model.accountBook.analyze.byWeek.AccountBookAnalyzeRecordByWeek
import com.uliga.domain.model.member.Member

@Immutable
data class AnalyzeUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val accountBookId: Long? = null,
    val member: Member? = null,
    val accountBookAnalyzeByMonthForCompare: AccountBookAnalyzeByMonthForCompare? = null,
    val accountBookAnalyzeRecordByWeek: AccountBookAnalyzeRecordByWeek? = null,
    val accountBookAnalyzeRecordByMonthForCategory: AccountBookAnalyzeRecordByMonthForCategory? = null,
    val accountBookAnalyzeFixedRecordByMonth: AccountBookAnalyzeFixedRecordByMonth? = null
) {
    companion object {
        fun empty() = AnalyzeUiState(
            isLoading = false,
            id = null,
            accountBookId = null,
            member = null,
            accountBookAnalyzeByMonthForCompare = null,
            accountBookAnalyzeRecordByWeek = null,
            accountBookAnalyzeRecordByMonthForCategory = null,
            accountBookAnalyzeFixedRecordByMonth = null
        )
    }
}