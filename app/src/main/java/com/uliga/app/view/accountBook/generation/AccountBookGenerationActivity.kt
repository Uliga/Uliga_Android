package com.uliga.app.view.accountBook.generation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.MyApplicationTheme
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.finance.showSettingDropDownMenu
import com.uliga.app.view.schedule.ScheduleBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState


@AndroidEntryPoint
class AccountBookGenerationActivity : ComponentActivity() {

    private val viewModel: AccountBookGenerationViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnrememberedMutableState")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val state = viewModel.collectAsState().value
                var selectedIndex by remember { mutableStateOf(-1) }
                val onItemClick = { index: Int -> selectedIndex = index }

                var isAccountBookGenerationState by rememberSaveable {
                    mutableStateOf(false)
                }

                var showAccountBookGenerationDialog by remember {
                    mutableStateOf(false)
                }

                if (isAccountBookGenerationState) {
                    showAccountBookGenerationDropDownMenu {
                        showAccountBookGenerationDialog = false
                    }
                }

                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    item {

                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.White),
                            title = {
                                Row {
                                    Image(
                                        modifier = Modifier
                                            .size(40.dp),
                                        painter = painterResource(
                                            id = R.drawable.uliga_logo
                                        ),
                                        contentDescription = "uliga logo"
                                    )

                                    Text(
                                        modifier = Modifier.padding(
                                            end = 12.dp
                                        ),
                                        text = "우리가",
                                        color = Primary,
                                        fontFamily = pretendard,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    isAccountBookGenerationState = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }
                        )

                    }

                    item {
                        Text(
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp
                                )
                                .clickable {

                                },
                            text = "가계부 목록",
                            color = Color.Black,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Spacer(
                            modifier = Modifier
                                .height(16.dp)
                        )
                    }

                    items(state.accountBooks?.accountBooks?.size ?: 0) { index ->

                        selectedAccountBook(
                            index = index,
                            selected = selectedIndex == index,
                            onClick = onItemClick
                        )

                    }

                    item {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentPadding = PaddingValues(
                                vertical = 16.dp
                            ),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Primary,
                            ),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {


                            }) {
                            Text(
                                color = Color.White,
                                fontFamily = pretendard,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                text = "우리가 시작하기"
                            )
                        }
                    }
                }
                
                Button(onClick = { viewModel.deleteMember() }) {
                    
                }
            }


        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun selectedAccountBook(
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp,
            )
            .clickable {
                onClick(index)
            }
            .border(
                width = 1.dp,
                color = if (selected) Primary else Grey500,
                shape = RoundedCornerShape(5.dp)
            )
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "안세훈",
            color = if (selected) Primary else Grey500,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = "님의 가계부",
            color = if (selected) Primary else Grey500,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(
            Modifier.weight(1f)
        )

        Image(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
                .alpha(if (selected) 1f else 0f)
                .size(40.dp),
            painter = painterResource(
                id = R.drawable.ic_account_book_select
            ),
            contentDescription = "uliga logo"
        )
    }

}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun unselectedAccountBook() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp,
            )
            .clickable {

            }
            .border(width = 1.dp, color = Grey500, shape = RoundedCornerShape(5.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "안세훈",
            color = Grey500,
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Text(
            text = "님의 공유 가계부",
            color = Grey500,
            fontFamily = pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        Spacer(
            Modifier.weight(1f)
        )

        Image(
            modifier = Modifier
                .alpha(0f)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
                .size(40.dp),
            painter = painterResource(
                id = R.drawable.ic_account_book_select
            ),
            contentDescription = null
        )
    }

}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showAccountBookGenerationDropDownMenu(
    showDialogRequest: () -> Unit,
) {
    val accountBookGenerationSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var isAccountBookGenerationSheetStateOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isAccountBookGenerationSheetStateOpen) {
        AccountBookGenerationBottomSheet(
            sheetState = accountBookGenerationSheetState,
            onDismissRequest = {
                isAccountBookGenerationSheetStateOpen = false
            }
        )
    }

    var expanded by remember { mutableStateOf(true) }
    val items = listOf(
        "가계부 추가하기"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    Color.White
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialogRequest()

                    when (index) {
                        0 -> {
                            isAccountBookGenerationSheetStateOpen = true
                        }
                    }

                }) {
                    Text(text = s)
                    if (index < 3) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }


    }
}