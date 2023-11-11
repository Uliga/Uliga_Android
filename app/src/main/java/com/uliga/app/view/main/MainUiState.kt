package com.uliga.app.view.main

import com.uliga.domain.model.member.Member

data class MainUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
    val member: Member? = null
) {
    companion object {
        fun empty() = MainUiState(
            isLoading = false,
            id = null,
            currentAccountInfo = null,
            member = null
        )
    }
}