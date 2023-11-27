package com.uliga.app.view.scheme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.UligaApplicationTheme
import com.uliga.app.view.auth.AuthActivity
import com.uliga.app.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class SchemeActivity : ComponentActivity() {

    private val viewModel: SchemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UligaApplicationTheme {

                val context = LocalContext.current

                /**
                 * SideEffect
                 */

                viewModel.collectSideEffect {
                    handleSideEffect(
                        sideEffect = it,
                        context = context
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center),
                        painter = painterResource(
                            id = R.drawable.uliga_logo
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private fun handleSideEffect(
    sideEffect: SchemeSideEffect,
    context: Context
) {
    when(sideEffect) {
        is SchemeSideEffect.MoveToAuthActivity -> {
            val intent = Intent(context, AuthActivity::class.java)
            context.startActivity(intent)
            (context as SchemeActivity).finish()
        }
        is SchemeSideEffect.MoveToMainActivity -> {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            (context as SchemeActivity).finish()
        }
        else -> { }
    }
}