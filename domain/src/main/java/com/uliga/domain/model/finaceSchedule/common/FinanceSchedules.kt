package com.uliga.domain.model.finaceSchedule.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceSchedules(
    val schedules: List<FinanceSchedule>,
): Parcelable, Serializable