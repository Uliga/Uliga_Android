package com.uliga.domain.model.finaceSchedule.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceScheduleDetailAssignment(
    val id: Long,
    val username: String,
    val value: Long
) : Parcelable, Serializable