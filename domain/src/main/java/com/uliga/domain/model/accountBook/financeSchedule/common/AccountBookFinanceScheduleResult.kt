package com.uliga.domain.model.accountBook.financeSchedule.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookFinanceScheduleResult(
    val name: String,
    val isIncome: Boolean,
    val notificationDate: Long,
    val value: Long,
    val assignments: List<AccountBookFinanceScheduleAssignment>,
) : Parcelable, Serializable



