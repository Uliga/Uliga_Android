package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookNumberOfMember(
    val count: Long
) : Parcelable, Serializable