package com.uliga.app.view.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.domain.model.accountBook.asset.month.AccountBookAssetMonth
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = Grey700,
                style = UligaTheme.typography.title2
            )
        }
    }
}

@Composable
fun Day(
    day: CalendarDay,
    accountBookAssetMonth: AccountBookAssetMonth?,
    onClick: (CalendarDay) -> Unit
) {

    val income = accountBookAssetMonth?.incomes?.filter { it.day.toInt() == day.date.dayOfMonth }
    val record = accountBookAssetMonth?.records?.filter { it.day.toInt() == day.date.dayOfMonth }

    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Black
                ),
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) }
            )
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Grey700 else Color.Gray,
            style = UligaTheme.typography.body2
        )

        Text(
            text = if (income.isNullOrEmpty()) "" else "+${income[0].value}",
            color = Color.Green,
            style = UligaTheme.typography.body9
        )

        Text(
            text = if (record.isNullOrEmpty()) "" else "-${record[0].value}",
            color = Color.Red,
            style = UligaTheme.typography.body9
        )
    }
}