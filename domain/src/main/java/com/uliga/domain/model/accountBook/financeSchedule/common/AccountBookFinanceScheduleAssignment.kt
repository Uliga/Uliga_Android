package com.uliga.domain.model.accountBook.financeSchedule.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookFinanceScheduleAssignment(
    val id: Long,
    val username: String,
    val value: Long
) : Parcelable, Serializable