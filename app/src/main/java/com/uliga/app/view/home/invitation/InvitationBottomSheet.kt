package com.uliga.app.view.home.invitation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.utils.TestTags
import com.uliga.app.view.component.VerticalSpacer
import com.uliga.app.view.component.item.InvitationItem
import com.uliga.app.view.home.HomeViewModel
import org.orbitmvi.orbit.compose.collectAsState


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun InvitationBottomSheet(
    sheetState: SheetState,
    viewModel: HomeViewModel,
    onDismissRequest: () -> Unit
) {

    val state = viewModel.collectAsState().value
    val invitation = state.member?.invitations

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        containerColor = White
    ) {
        LazyColumn(
            modifier = Modifier
                .height(400.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                    ).testTag(TestTags.NEW_INVITATION),
                    text = "새로운 가계부 초대",
                    color = Grey700,
                    style = UligaTheme.typography.title3,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                VerticalSpacer(height = 4.dp)
            }

            items(invitation?.size ?: 0) { idx ->
                Column {

                    InvitationItem(
                        invitation = invitation,
                        idx = idx,
                        onDeclineRequest = {
                            val memberInvitation = invitation?.get(idx) ?: return@InvitationItem

                            viewModel.postAccountBookInvitationReply(
                                id = memberInvitation.id,
                                memberName = memberInvitation.memberName,
                                accountBookName = memberInvitation.accountBookName,
                                join = false
                            )
                        },
                        onAcceptRequest = {
                            val memberInvitation = invitation?.get(idx) ?: return@InvitationItem

                            viewModel.postAccountBookInvitationReply(
                                id = memberInvitation.id,
                                memberName = memberInvitation.memberName,
                                accountBookName = memberInvitation.accountBookName,
                                join = true
                            )
                        }
                    )
                }
            }
        }

    }
}