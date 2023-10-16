package com.uliga.domain.model.accountBook.asset.month

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAssetDayIncome(
    val day: Long,
    val value: Long
) : Parcelable, Serializable