package com.uliga.domain.model.accountBook.asset

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAsset(
    val income: AccountBookAssetIncome,
    val record: AccountBookAssetRecord,
    val budget: AccountBookAssetBudget,
) : Parcelable, Serializable