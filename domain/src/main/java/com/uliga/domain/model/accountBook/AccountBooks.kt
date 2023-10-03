package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBooks(
    val accountBooks: List<AccountBook>
): Parcelable, Serializable
