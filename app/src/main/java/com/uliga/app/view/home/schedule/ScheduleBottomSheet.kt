package com.uliga.app.view.home.schedule

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import com.uliga.app.view.budget.BudgetSettingBottomSheetCompose


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest
        })
    {

        BudgetSettingBottomSheetCompose()
    }
}
