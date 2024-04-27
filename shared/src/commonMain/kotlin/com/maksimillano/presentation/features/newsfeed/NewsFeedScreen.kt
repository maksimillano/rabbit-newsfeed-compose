package com.maksimillano.presentation.features.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.maksimillano.presentation.component.colorThemed
import com.maksimillano.presentation.features.FeatureConfig

@Composable
fun NewsFeedScreen(newsFeedComponent: NewsFeedComponent) {
//    Column(
//        modifier = Modifier.fillMaxSize()
//            .background(Color(0xff8fff5e))
//            .clickable { newsFeedComponent.navigator.onButtonBack() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("${NewsFeedComponentImpl::class.simpleName}")
//
//        Button(onClick = { newsFeedComponent.navigator.onNavigate(FeatureConfig.Wall(1))}) { Text(text = "Wall") }
//        Button(onClick = { newsFeedComponent.navigator.onNavigate(FeatureConfig.Comments)}) { Text(text = "Comments") }
//    }

    FeedList(newsFeedComponent)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedList(
    newsFeedComponent: NewsFeedComponent
) {
    val state by newsFeedComponent.viewModel.state.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.refreshStatus == RefreshStatus.Show,
        onRefresh = { newsFeedComponent.viewModel.onNewEvent(NewsFeedMviEvent.LoadLatest) }
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