package com.uliga.domain.model.accountBook.asset.month

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAssetMonth(
    val incomes: List<AccountBookAssetDayIncome>,
    val records: List<AccountBookAssetDayRecord>,
) : Parcelable, Serializable