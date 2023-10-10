package com.uliga.domain.model.financeSchedule.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceScheduleDetail(
    val info: FinanceScheduleDetailInfo,
    val assignments: List<FinanceScheduleDetailAssignment>,
) : Parcelable, Serializable