package com.uliga.data.model.financeSchedule.update

import com.uliga.domain.model.financeSchedule.update.FinanceScheduleUpdateAssignment
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateAssignmentData(
    val additionalProp1: Long,
    val additionalProp2: Long,
    val additionalProp3: Long,
)

fun FinanceScheduleUpdateAssignment.toData() = FinanceScheduleUpdateAssignmentData(
    additionalProp1 = additionalProp1,
    additionalProp2 = additionalProp2,
    additionalProp3 = additionalProp3
)

fun FinanceScheduleUpdateAssignmentData.toDomain() = FinanceScheduleUpdateAssignment(
    additionalProp1 = additionalProp1,
    additionalProp2 = additionalProp2,
    additionalProp3 = additionalProp3
)