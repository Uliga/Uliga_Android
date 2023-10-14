package com.uliga.domain.model.financeSchedule.update

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceScheduleUpdate(
    val id: Long,
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: HashMap<Long, Long>
) : Parcelable, Serializable