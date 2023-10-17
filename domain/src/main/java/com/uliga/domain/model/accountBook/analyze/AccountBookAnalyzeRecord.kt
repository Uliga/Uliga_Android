package com.uliga.domain.model.accountBook.analyze

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookAnalyzeRecord(
    val day: Long,
    val value: Long
) : Parcelable, Serializable