package com.uliga.app.view.main

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uliga.app.base.ComposeActivity
import com.uliga.app.ui.theme.MyApplicationTheme
import com.uliga.app.view.budget.BudgetSettingBottomSheetCompose
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComposeActivity<MainViewModel, MainUiEvent>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen(viewModel)
            }
        }
    }
}