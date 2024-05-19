package com.maksimillano.presentation.features.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsFeedScreen(newsFeedComponent: NewsFeedComponent) {
    val state by newsFeedComponent.viewModel.state.collectAsState()
    val isRefreshing = state.refreshStatus == RefreshStatus.Show
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { newsFeedComponent.viewModel.onNewEvent(NewsFeedMviEvent.RefreshPage) }
    )

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        FeedList(newsFeedComponent, state)
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            contentColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun FeedList(
    newsFeedComponent: NewsFeedComponent,
    state: NewsFeedState
) {
    val listState: LazyListState = rememberLazyListState()
    val loadMorePercent = 0.75f
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.last().index
                val totalItemsCount = listState.layoutInfo.totalItemsCount
                val percentage = lastVisibleItemIndex / totalItemsCount.toFloat()
                if (percentage >= loadMorePercent) {
                    newsFeedComponent.viewModel.onNewEvent(NewsFeedMviEvent.LoadBefore)
                }
                return Offset.Zero
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(
            state.feedItemEntries,
            key = { it.key }
        ) { entry ->
            entry.onBind()
        }
    }
}