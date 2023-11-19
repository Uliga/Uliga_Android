package com.uliga.app.view.profile

import com.uliga.domain.model.member.Member
import javax.annotation.concurrent.Immutable

@Immutable
data class ProfileUiState(
    val isLoading: Boolean = false,
    val accountBookId: Long? = null,
    val id: Long? = null,
    val member: Member? = null
) {
    companion object {
        fun empty() = ProfileUiState(
            isLoading = false,
            accountBookId = null,
            id = null,
            member = null
        )
    }
}