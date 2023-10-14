package com.uliga.domain.model.accountBook.financeSchedule

import android.os.Parcelable
import com.uliga.domain.model.accountBook.financeSchedule.common.AccountBookFinanceScheduleResult
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookFinanceScheduleResponse(
    val result: AccountBookFinanceScheduleResult
) : Parcelable, Serializable