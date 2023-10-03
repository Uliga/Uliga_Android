package com.uliga.app.view.accountBook.input

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.uliga.app.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AccountBookForInputActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                AccountBookForInputBottomSheetCompose()

            }

        }
    }
}