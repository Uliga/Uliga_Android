package com.uliga.app.view.component

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White

@Composable
fun AgreeCheckbox(
    text: String,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckChange,
        colors = CheckboxDefaults.colors(
            checkedColor = Grey500,
        )
    )

    Text(
        color = Grey500,
        style = UligaTheme.typography.body12,
        text = text,
    )
}