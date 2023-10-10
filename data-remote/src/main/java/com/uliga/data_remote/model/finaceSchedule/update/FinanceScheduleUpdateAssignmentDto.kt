package com.uliga.data_remote.model.finaceSchedule.update

import com.uliga.data.model.finaceSchedule.update.FinanceScheduleUpdateAssignmentData
import kotlinx.serialization.Serializable


@Serializable
data class FinanceScheduleUpdateAssignmentDto(
    val additionalProp1: Long,
    val additionalProp2: Long,
    val additionalProp3: Long,
)

fun FinanceScheduleUpdateAssignmentData.toDto() = FinanceScheduleUpdateAssignmentDto(
    additionalProp1 = additionalProp1,
    additionalProp2 = additionalProp2,
    additionalProp3 = additionalProp3
)

fun FinanceScheduleUpdateAssignmentDto.toData() = FinanceScheduleUpdateAssignmentData(
    additionalProp1 = additionalProp1,
    additionalProp2 = additionalProp2,
    additionalProp3 = additionalProp3
)