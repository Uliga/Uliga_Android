package com.uliga.app.view.accountBook.input

import androidx.core.text.isDigitsOnly
import com.uliga.app.base.BaseViewModel
import com.uliga.app.utils.ToastMessages.ACCOUNT_BOOK_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.ACCOUNT_PLEASE_SELECT
import com.uliga.app.utils.ToastMessages.DATE_INFO_BRING_FAIL
import com.uliga.app.utils.ToastMessages.EMAIL_PLEASE_SELECT
import com.uliga.app.utils.ToastMessages.MEMBER_INFO_GET_FAILURE
import com.uliga.app.utils.ToastMessages.PAYMENT_METHOD_PLEASE_SELECT
import com.uliga.app.utils.ToastMessages.VALUE_PLEASE_SELECT
import com.uliga.domain.model.accountBook.transaction.AccountBookTransactionRequest
import com.uliga.domain.usecase.accountbook.PostAccountBookTransactionUseCase
import com.uliga.domain.usecase.accountbook.local.FetchCurrentAccountBookIdUseCase
import com.uliga.domain.usecase.userAuth.local.FetchIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AccountBookInputViewModel @Inject constructor(
    private val fetchIdUseCase: FetchIdUseCase,
    private val postAccountBookTransactionUseCase: PostAccountBookTransactionUseCase,
    private val fetchCurrentAccountBookIdUseCase: FetchCurrentAccountBookIdUseCase
) : ContainerHost<AccountBookInputUiState, AccountBookInputSideEffect>, BaseViewModel() {

    override val container = container<AccountBookInputUiState, AccountBookInputSideEffect>(
        AccountBookInputUiState.empty()
    )

    init {
        initialize()
    }

    fun initialize() {
        observeCurrentAccountBookId()
    }

    fun observeCurrentAccountBookId() = intent {
        launch {
            fetchCurrentAccountBookIdUseCase().collectLatest { accountBookId ->
                updateAccountBookId(accountBookId)
                fetchId()
            }
        }
    }

    fun postAccountBookTransaction(
        transactionType: String,
        category: String,
        payment: String,
        date: String?,
        account: String,
        value: String,
        memo: String,
    ) = intent {
        launch {

            val accountBookId = state.accountBookId
            if (accountBookId == null) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(ACCOUNT_BOOK_INFO_GET_FAILURE))
                updateIsLoading(false)
                return@launch
            }

            if (date == null) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(DATE_INFO_BRING_FAIL))
                updateIsLoading(false)
                return@launch
            }

            if (category.isEmpty()) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(EMAIL_PLEASE_SELECT))
                updateIsLoading(false)
                return@launch
            }

            if (payment.isEmpty()) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(PAYMENT_METHOD_PLEASE_SELECT))
                updateIsLoading(false)
                return@launch
            }

            if (account.isEmpty()) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(ACCOUNT_PLEASE_SELECT))
                updateIsLoading(false)
                return@launch
            }

            if (value.isEmpty() || !value.isDigitsOnly()) {
                postSideEffect(AccountBookInputSideEffect.ToastMessage(VALUE_PLEASE_SELECT))
                updateIsLoading(false)
                return@launch
            }

            val accountBookTransactionRequest = AccountBookTransactionRequest(
                id = accountBookId,
                category = category,
                payment = payment,
                date = date,
                account = account,
                value = value.toLong(),
                memo = memo,
                sharedAccountBook = listOf()
            )

            postAccountBookTransactionUseCase(transactionType, accountBookTransactionRequest)
                .onSuccess {
                    postSideEffect(AccountBookInputSideEffect.Finish)
                }
                .onFailure {
                    postSideEffect(AccountBookInputSideEffect.Finish)
                }
        }
    }

    private fun fetchId() = intent {
        launch {
            fetchIdUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            id = it
                        )
                    }
                }
                .onFailure {
                    postSideEffect(AccountBookInputSideEffect.ToastMessage(MEMBER_INFO_GET_FAILURE))
                }
        }
    }

    suspend fun updateAccountBookId(accountBookId: Long?) = intent {
        launch {
            reduce {
                state.copy(
                    accountBookId = accountBookId
                )
            }
        }
    }

    override fun onShowErrorToast(message: String) = intent {
        postSideEffect(AccountBookInputSideEffect.ToastMessage(message))
    }

    override fun updateIsLoading(isLoading: Boolean) = intent {
        reduce {
            state.copy(
                isLoading = isLoading,
            )
        }
    }

}