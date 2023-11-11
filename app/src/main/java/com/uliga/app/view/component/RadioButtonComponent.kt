package com.uliga.app.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaTheme

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ClassifierRadioButton(
    selectedItem: String,
    scheduleType: String,
    onSelectRequest: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .border(
                width = 1.dp,
                color = if (selectedItem == scheduleType) {
                    Primary
                } else {
                    Grey400
                },
                shape = UligaTheme.shapes.large
            )
            .selectable(
                selected = (selectedItem == scheduleType),
                onClick = {
                    onSelectRequest(scheduleType)
                },
                role = Role.RadioButton
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            color = if (selectedItem == scheduleType) {
                Primary
            } else {
                Grey400
            },
            style = UligaTheme.typography.body11,
            text = scheduleType
        )
    }
}