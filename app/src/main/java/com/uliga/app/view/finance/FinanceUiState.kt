package com.uliga.app.view.finance

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetDay
import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetMonth
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionResponse
import com.uliga.domain.model.member.Member

@Immutable
data class FinanceUiState(
    val isLoading: Boolean = false,
    val id: Long? = null,
    val accountBookId: Long? = null,
    val member: Member? = null,
    val currentAccountBookAsset: AccountBookAssetMonth? = null,
    val currentAccountBookAssetDay: AccountBookAssetDay? = null,
    val currentAccountBookTransaction: AccountBookTransactionResponse? = null
) {
    companion object {
        fun empty() = FinanceUiState(
            isLoading = false,
            id = null,
            member = null,
            accountBookId = null,
            currentAccountBookAsset = null,
            currentAccountBookAssetDay = null,
            currentAccountBookTransaction = null
        )
    }
}