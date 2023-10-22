package com.uliga.app.view.analyze

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.accountBook.analyze.byMonth.compare.AccountBookAnalyzeByMonthForCompare

@Immutable
data class AnalyzeUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
    val accountBookAnalyzeByMonthForCompare: AccountBookAnalyzeByMonthForCompare? = null
) {
    companion object {
        fun empty() = AnalyzeUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null,
            accountBookAnalyzeByMonthForCompare = null
        )
    }
}