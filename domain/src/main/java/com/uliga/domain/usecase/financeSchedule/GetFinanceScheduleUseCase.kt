package com.uliga.domain.usecase.financeSchedule

import com.uliga.domain.model.financeSchedule.common.FinanceSchedule
import com.uliga.domain.repository.FinanceScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFinanceScheduleUseCase @Inject constructor(
    private val financeScheduleRepository: FinanceScheduleRepository
) {
    suspend operator fun invoke(): Result<FinanceSchedule> {
        return financeScheduleRepository.getFinanceSchedule()
    }
}