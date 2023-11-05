package com.uliga.app.view.component

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uliga.app.ui.theme.Grey300

@Composable
fun OneThicknessDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        modifier = modifier,
        color = Grey300,
        thickness = 1.dp
    )
}