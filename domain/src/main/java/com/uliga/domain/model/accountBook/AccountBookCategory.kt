package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookCategory(
    val id: Long,
    val value: String,
    val label: String,
): Parcelable, Serializable