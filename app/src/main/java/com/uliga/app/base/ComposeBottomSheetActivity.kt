package com.uliga.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


abstract class ComposeBottomSheetActivity<VM : ComposeViewModel<*, E>, E : ComposeViewModel.UiEvent>
    : ComponentActivity() {

    protected val TAG: String = javaClass.simpleName

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchEventObserver()
    }

    private fun launchEventObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest {
                    onEventObserved(it)
                }
            }
        }
    }

    protected open suspend fun onEventObserved(event: ComposeViewModel.UiEvent) {}

}