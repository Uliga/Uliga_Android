package com.uliga.domain.usecase.financeSchedule

import com.uliga.domain.model.financeSchedule.common.FinanceSchedules
import com.uliga.domain.repository.FinanceScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFinanceScheduleUseCase @Inject constructor(
    private val financeScheduleRepository: FinanceScheduleRepository
) {
    suspend operator fun invoke(): Result<FinanceSchedules> {
        return financeScheduleRepository.getFinanceSchedule()
    }
}