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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
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



                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(
                            vertical = 16.dp,
                        )
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
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
                }

                val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
                var isSheetOpen by rememberSaveable {
                    mutableStateOf(false)
                }

                if(isSheetOpen) {
                    AccountBookGenerationBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            isSheetOpen = false
                        }
                    )
                }
                Button(onClick = { isSheetOpen = true }) {

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