package com.uliga.app.view.component.item

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.CustomGray300
import com.uliga.app.ui.theme.CustomGrey500
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.LightBlue
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.domain.model.accountBook.asset.day.AccountBookAssetItem

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TransactionItem(
    currentAccountBookAssetDay: AccountBookAssetItem,
    onDeleteRequest: (AccountBookAssetItem) -> Unit
) {

    VerticalSpacer(height = 8.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            )
            .clip(UligaTheme.shapes.small)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        Log.d("FinanceScreen", "onLongPress")
                    }
                )
            }
            .background(CustomGray300),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(52.dp),
                painter = painterResource(
                    id = R.drawable.ic_transaction
                ),
                contentDescription = null
            )

            Text(
                text = if (currentAccountBookAssetDay.type == "INCOME") "수입" else "지출",
                color = LightBlue,
                style = UligaTheme.typography.body10,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            VerticalSpacer(height = 4.dp)
        }


        Column(
            modifier = Modifier.weight(4f)
        ) {
            Text(
                text = "${currentAccountBookAssetDay.category} / ${currentAccountBookAssetDay.account}",
                color = Grey700,
                style = UligaTheme.typography.body11,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = "${currentAccountBookAssetDay.value}원 / ${currentAccountBookAssetDay.payment} / by. ${currentAccountBookAssetDay.creator}",
                color = CustomGrey500,
                style = UligaTheme.typography.body10,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = currentAccountBookAssetDay.memo,
                color = LightBlue,
                style = UligaTheme.typography.body10,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }

        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        color = Color.Black
                    ),
                    onClick = {
                        onDeleteRequest(currentAccountBookAssetDay)
                    }
                )
                .padding(4.dp),
            painter = painterResource(
                id = R.drawable.ic_delete
            ),
            contentDescription = null
        )


    }
}