package com.uliga.app.view.accountBook.generation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uliga.app.R
import com.uliga.app.ui.theme.Grey100
import com.uliga.app.ui.theme.Grey400
import com.uliga.app.ui.theme.Grey500
import com.uliga.app.ui.theme.Primary
import com.uliga.app.ui.theme.pretendard
import com.uliga.app.view.accountBook.selection.TmpCompose

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AccountBookGenerationBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest()
        })
    {

        AccountBookGenerationBottomSheetCompose()

    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AccountBookGenerationBottomSheetCompose() {

    var accountBookName by remember {
        mutableStateOf("")
    }

    var accountBookInvitation by remember {
        mutableStateOf("")
    }

    val invitiationElementList = remember {
        mutableStateListOf("ansehoon1999@gmail.com", "ansehoon1999@gmail.com", "ansehoon1999@gmail.com")
    }
    
    var accountBookCategory by remember {
        mutableStateOf("")
    }

    val categoryElementList = remember {
        mutableStateListOf("식비", "식비","카페 간식", "생활", "편의점,마트, 잡화")
    }
    


    LazyColumn(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
            .wrapContentSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {

        item {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = "공유 가계부 생성",
                    color = Color.Black,
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(
                    Modifier.weight(1f)
                )

                Image(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(
                        id = R.drawable.ic_cancel
                    ),
                    contentDescription = null
                )
            }
        }

        item {
            Text(
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                text = "가계부 이름",
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                androidx.compose.material.TextField(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Grey400,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth(),
                    value = accountBookName,
                    onValueChange = {
                        accountBookName = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "가계부 이름을 입력해주세요.",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )

            }
        }

        item {
            Text(
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                text = "사용자 초대",
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                androidx.compose.material.TextField(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Grey400,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .wrapContentWidth()
                        .weight(3.5f),
                    value = accountBookInvitation,
                    onValueChange = {
                        accountBookInvitation = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "사용자의 이메일을 입력해주세요.",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )

                Button(
                    enabled = true,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE9EEFF)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        invitiationElementList.add(accountBookInvitation)
                    }) {
                    Text(
                        color = Primary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        text = "초대"
                    )
                }

            }

            ChipsWithDelete(
                elements = invitiationElementList,
                onDeleteButtonClicked = { content ->
                    invitiationElementList.remove(content)
                }
            )

        }

        item {
            Text(
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                text = "카테고리 추가",
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                androidx.compose.material.TextField(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Grey400,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .wrapContentWidth()
                        .weight(3.5f),
                    value = accountBookCategory,
                    onValueChange = {
                        accountBookCategory = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "추가할 카테고리명을 입력해주세요.",
                            color = Grey500,
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )

                Button(
                    enabled = true,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE9EEFF)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        categoryElementList.add(accountBookCategory)
                    }) {
                    Text(
                        color = Primary,
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        text = "추가"
                    )
                }

            }
        }

        item {
            Text(
                text = "현재 카테고리",
                color = Grey500,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Chips(elements = categoryElementList)
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
                    text = "공유 가계부 만들기"
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsWithDelete(
    elements: List<String>,
    onDeleteButtonClicked: (String) -> Unit
) {
    FlowRow {
        elements.forEachIndexed { idx, _ ->
            ChipWithDelete(
                text = elements[idx],
                onDeleteButtonClicked = { content  ->
                    onDeleteButtonClicked(content)
                }
            )

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Chips(
    elements: List<String>,
) {
    FlowRow {
        elements.forEachIndexed { idx, chipState ->
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
        contentColor = Color.Black,
        shape = RoundedCornerShape(28.dp),
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Row(modifier = Modifier) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
            )

        }
    }
}

@Composable
fun ChipWithDelete(
    modifier: Modifier = Modifier,
    text: String,
    onDeleteButtonClicked: (String) -> Unit
) {
    Surface(
        color = Grey100,
        contentColor = Color.Black,
        shape = RoundedCornerShape(28.dp),
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
        )  {
            Text(
                text = text,

            )

            Spacer(modifier = Modifier.width(4.dp))

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

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun Preview() {
    AccountBookGenerationBottomSheetCompose()
}