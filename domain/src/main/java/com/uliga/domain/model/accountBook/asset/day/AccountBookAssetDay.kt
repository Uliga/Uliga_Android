package com.uliga.domain.model.accountBook.asset.day

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAssetDay(
    val items: List<AccountBookAssetItem>
) : Parcelable, Serializable