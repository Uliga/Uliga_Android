package com.uliga.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import kotlinx.coroutines.flow.collectLatest

abstract class ComposeActivity<VM : ComposeViewModel<*, EVENT>, EVENT : ComposeViewModel.UiEvent> :
    ComponentActivity() {
    protected val TAG: String = javaClass.simpleName

    protected abstract val viewModel: VM

    @StyleRes
    protected open val themeResId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchEventObserver()
    }

    private fun launchEventObserver() = repeatOnStarted {
        viewModel.uiEvent.collectLatest {
            onEventObserved(it)
        }
    }

    protected open suspend fun onEventObserved(event: ComposeViewModel.UiEvent) {}

}