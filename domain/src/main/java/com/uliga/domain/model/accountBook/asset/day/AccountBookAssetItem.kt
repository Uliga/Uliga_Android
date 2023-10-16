package com.uliga.domain.model.accountBook.asset.day

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAssetItem(
    val id: Long,
    val value: Long,
    val payment: String,
    val account: String,
    val memo: String,
    val year: Long,
    val month: Long,
    val day: Long,
    val type: String,
    val creator: String,
    val category: String,
    val avatarUrl: String
) : Parcelable, Serializable