package com.uliga.domain.model.accountBook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AccountBook(
    val info: AccountBookInfo,
    val numberOfMember: AccountBookNumberOfMember,
    val members: List<AccountBookMember>,
    val categories: List<AccountBookCategory>
) : Parcelable, Serializable