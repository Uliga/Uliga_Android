package com.uliga.domain.model.finaceSchedule.update

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceScheduleUpdateAssignment(
    val additionalProp1: Long,
    val additionalProp2: Long,
    val additionalProp3: Long,
) : Parcelable, Serializable