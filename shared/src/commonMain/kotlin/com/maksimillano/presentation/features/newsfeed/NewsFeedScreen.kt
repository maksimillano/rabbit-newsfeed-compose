package com.maksimillano.presentation.features.newsfeed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsFeedScreen(newsFeedComponent: NewsFeedComponent) {
    val state by newsFeedComponent.viewModel.state.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.refreshStatus == RefreshStatus.Show,
        onRefresh = { newsFeedComponent.viewModel.onNewEvent(NewsFeedMviEvent.LoadLatest) }
    )

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        FeedList(newsFeedComponent, state)
        PullRefreshIndicator(
            refreshing = state.refreshStatus == RefreshStatus.Show,
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
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            state.feedItemEntries,
            key = { it.key }
        ) { entry ->
            entry.onBind()
        }
    }
}