package com.maksimillano.presentation.features.newsfeed

import androidx.compose.runtime.Immutable
import com.maksimillano.presentation.base.mvi.MviState
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

@Immutable
data class NewsFeedState(
    val feedItemEntries: List<FeedDisplayEntry> = emptyList(),
    val refreshStatus: RefreshStatus = RefreshStatus.Missing,
    val updateNotification: UpdateNotification = UpdateNotification.Missing
) : MviState

sealed interface RefreshStatus {
    data object Show : RefreshStatus
    data object Missing : RefreshStatus
}

sealed interface UpdateNotification {
    data object Show : UpdateNotification
    data object Missing : UpdateNotification
}