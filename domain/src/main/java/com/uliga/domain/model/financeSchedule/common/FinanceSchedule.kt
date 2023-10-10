package com.uliga.domain.model.financeSchedule.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class FinanceSchedule(
    val id: Long,
    val notificationDay: Long,
    val name: String,
    val isIncome: Boolean,
    val value: Long,
    val creatorId: Long,
    val creator: String,
    val accountBookName: String,
): Parcelable, Serializable