package com.maksimillano.presentation.features.newsfeed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maksimillano.presentation.component.colorThemed

@Composable
fun NewsFeedScreen(newsFeedComponent: NewsFeedComponent) {
    FeedList(newsFeedComponent)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedList(
    newsFeedComponent: NewsFeedComponent
) {
    val state by newsFeedComponent.state.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.refreshStatus == RefreshStatus.Show,
        onRefresh = { newsFeedComponent.viewModel.postEvent(NewsFeedMviEvent.LoadLatest) }
    )

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
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

        PullRefreshIndicator(
            refreshing = state.refreshStatus == RefreshStatus.Show,
            state = pullRefreshState,
            contentColor = colorThemed { it.accentColor },
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}