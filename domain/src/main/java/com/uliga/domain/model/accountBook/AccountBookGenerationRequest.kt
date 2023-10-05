package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookGenerationRequest(
    val name: String,
    val categories: List<String>,
    val emails: List<String>,
    val relationship: String
) : Parcelable, Serializable