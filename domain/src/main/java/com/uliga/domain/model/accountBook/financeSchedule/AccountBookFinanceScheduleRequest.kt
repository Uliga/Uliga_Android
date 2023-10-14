package com.uliga.domain.model.accountBook.financeSchedule

import android.os.Parcelable
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookFinanceScheduleRequest(
    val id: Long,
    val schedules: List<AccountBookFinanceScheduleResult>,
) : Parcelable, Serializable


