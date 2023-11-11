package com.uliga.app.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey100
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme

@Composable
fun ChipWithDelete(
    modifier: Modifier = Modifier,
    text: String,
    onDeleteButtonClicked: (String) -> Unit
) {
    Surface(
        color = Grey100,
        contentColor = Grey700,
        shape = UligaTheme.shapes.xlarge,
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Grey500,
                style = UligaTheme.typography.body12
            )

            HorizontalSpacer(width = 4.dp)

            Image(
                modifier = Modifier
                    .size(12.dp)
                    .clickable {
                        onDeleteButtonClicked(text)
                    },
                painter = painterResource(
                    id = R.drawable.ic_cancel
                ),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsWithDelete(
    modifier: Modifier = Modifier,
    elements: List<String>,
    onDeleteButtonClicked: (String) -> Unit
) {
    FlowRow(modifier = modifier) {
        elements.forEachIndexed { idx, _ ->
            ChipWithDelete(
                text = elements[idx],
                onDeleteButtonClicked = { content ->
                    onDeleteButtonClicked(content)
                }
            )

            HorizontalSpacer(width = 8.dp)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Chips(
    modifier: Modifier = Modifier,
    elements: List<String>,
) {
    FlowRow(modifier = modifier) {
        elements.forEachIndexed { idx, _ ->
            Chip(
                text = elements[idx],
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(
        color = Grey100,
        contentColor = Grey700,
        shape = UligaTheme.shapes.xlarge,
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Row(modifier = Modifier) {
            Text(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    ),
                text = text,
                color = Grey500,
                style = UligaTheme.typography.body12
            )
        }
    }
}