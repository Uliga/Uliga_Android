package com.uliga.app.view.analyze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uliga.app.ui.theme.Black
import com.uliga.app.ui.theme.Grey700
import com.uliga.app.ui.theme.UligaTheme
import com.uliga.app.ui.theme.White
import com.uliga.app.view.main.MainUiState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnalyzeScreen(
    viewModel: AnalyzeViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 2 }
    )

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            viewModel.initialize()
        }
    )

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                ),
                title = {
                    Text(
                        text = "분석",
                        color = Grey700,
                        style = UligaTheme.typography.title2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize()
        ) {
            AnalyzeTabs(pagerState = pagerState)
            AnalyzeTabsContent(
                pagerState = pagerState,
                viewModel = viewModel
            )

            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState
            )
        }
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

    TabRow(
        modifier = Modifier
            .shadow(5.dp)
            .wrapContentWidth(),
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
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = Black
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
                        color = if (pagerState.currentPage == index) Grey700 else Color.LightGray,
                        style = UligaTheme.typography.body12
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
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> AnalyzeByTimeScreen(viewModel)
            1 -> AnalyzeByCategoryScreen(viewModel)
        }
    }
}