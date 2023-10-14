package com.uliga.domain.model.accountBook.asset

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAssetBudget(
    val value: Long
) : Parcelable, Serializable