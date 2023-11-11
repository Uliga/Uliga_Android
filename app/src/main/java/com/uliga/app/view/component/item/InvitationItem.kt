package com.uliga.app.view.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.R
import com.uliga.app.ui.theme.CustomGrey500
import com.uliga.app.ui.theme.Grey200
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.view.component.DeclineButton
import com.uliga.app.view.component.HorizontalSpacer
import com.uliga.app.view.component.OneThicknessDivider
import com.uliga.app.view.component.PositiveButton
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.domain.model.member.MemberInvitation

@Composable
fun InvitationItem(
    invitation: List<MemberInvitation>?,
    idx: Int,
    onDeclineRequest: () -> Unit,
    onAcceptRequest: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            modifier = Modifier
                .size(52.dp),
            painter = painterResource(
                id = R.drawable.ic_invitation_logo
            ),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "${invitation?.get(idx)?.memberName ?: ""}님의 초대",
                color = CustomGrey500,
                style = UligaTheme.typography.body12,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                text = "${invitation?.get(idx)?.accountBookName}",
                color = Grey700,
                style = UligaTheme.typography.body13,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        DeclineButton(
            modifier = Modifier
                .wrapContentSize(),
            text = "거절",
            contentPadding = PaddingValues(
                vertical = 12.dp,
                horizontal = 12.dp
            ),
            onClick = {
                onDeclineRequest()
            }
        )


        HorizontalSpacer(width = 8.dp)

        PositiveButton(
            modifier = Modifier
                .wrapContentSize(),
            text = "수락",
            contentPadding = PaddingValues(
                vertical = 12.dp,
                horizontal = 12.dp
            ),
            onClick = {
                onAcceptRequest()
            }
        )

        HorizontalSpacer(width = 16.dp)
    }

    VerticalSpacer(height = 12.dp)

    OneThicknessDivider()
}