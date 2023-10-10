package com.uliga.domain.usecase.financeSchedule

import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdate
import com.uliga.domain.repository.FinanceScheduleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFinanceScheduleDetailUseCase @Inject constructor(
    private val financeScheduleRepository: FinanceScheduleRepository
) {
    suspend operator fun invoke(id: Long): Result<FinanceScheduleUpdate> {
        return financeScheduleRepository.getFinanceScheduleDetail(id)
    }
}