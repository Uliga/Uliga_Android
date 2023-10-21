package com.uliga.app.view.analyze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.uliga.app.customTabIndicatorOffset
import com.uliga.app.ui.theme.pretendard
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeScreen(
    navController: NavController,
    viewModel: AnalyzeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        AnalyzeTabs(pagerState = pagerState)
        AnalyzeTabsContent(
            pagerState = pagerState,
            viewModel = viewModel
        )
    }


}

private val analyzeScreenList = listOf("시간별", "카테고리별")


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnalyzeTabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val selectedTabIndex = pagerState.currentPage

    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(analyzeScreenList.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }


    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .clickable {

                    },
                text = "안세훈님의 가계부",
                color = Color.Black,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
            }
        }
    )


    TabRow(
        modifier = Modifier.wrapContentWidth(),
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.White,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        }
    ) {
        analyzeScreenList.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = analyzeScreenList[index],
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        },
                        color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                    )
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selected = pagerState.currentPage == index,
            )


        }


    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalyzeTabsContent(
    pagerState: PagerState,
    viewModel: AnalyzeViewModel
) {
    HorizontalPager(
        state = pagerState,
        pageCount = analyzeScreenList.size
    ) { page ->
        when (page) {
            0 -> AnalyzeByTimeScreen(viewModel)
            1 -> AnalyzeByCategoryScreen(viewModel)
        }
    }
}