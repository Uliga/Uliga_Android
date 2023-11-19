package com.uliga.app.view.schedule

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.model.member.Member

@Immutable

data class ScheduleGenerationUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val accountBookId: Long? = null,
    val financeSchedules: FinanceSchedules? = null,
    val member: Member? = null
) {
    companion object {
        fun empty() = ScheduleGenerationUiState(
            isLoading = false,
            id = null,
            accountBookId = null,
            financeSchedules = null
        )
    }
}