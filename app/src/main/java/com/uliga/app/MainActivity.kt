package com.uliga.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.uliga.app.base.ComposeActivity
import com.uliga.app.ui.theme.UligaApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComposeActivity<MainViewModel, MainUiEvent>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UligaApplicationTheme {
                MainScreen(viewModel)
            }
        }
    }
}