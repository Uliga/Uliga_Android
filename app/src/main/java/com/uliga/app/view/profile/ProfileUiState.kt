package com.uliga.app.view.profile

import com.uliga.domain.model.member.Member
import javax.annotation.concurrent.Immutable

@Immutable
data class ProfileUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
    val member: Member? = null
) {
    companion object {
        fun empty() = ProfileUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null,
            member = null
        )
    }
}