package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class AccountBookGenerationResponse(
    val id: Long,
    val name: String,
    val isPrivate: Boolean,
    val relationShip: String,
): Parcelable, Serializable