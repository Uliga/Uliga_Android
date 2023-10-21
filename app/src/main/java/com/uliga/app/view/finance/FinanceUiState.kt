package com.uliga.app.view.finance

import androidx.compose.runtime.Immutable
import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetMonth
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionResponse

@Immutable
data class FinanceUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val id: Long? = null,
    val currentAccountInfo: Pair<String, Long>? = null,
    val currentAccountBookAsset: AccountBookAssetMonth? = null,
    val currentAccountBookTransaction: AccountBookTransactionResponse? = null
) {
    companion object {
        fun empty() = FinanceUiState(
            isLoading = false,
            exception = null,
            id = null,
            currentAccountInfo = null,
            currentAccountBookAsset = null,
            currentAccountBookTransaction = null
        )
    }
}