package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBookMember(
    val id: Long,
    val username: String,
    val accountBookAuthority: String,
    val avatarUrl: String,
    val email: String,
): Parcelable, Serializable