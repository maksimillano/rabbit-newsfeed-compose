package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.presentation.base.mvi.MviState
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

data class NewsFeedState(
    val feedItemEntries: List<FeedDisplayEntry> = emptyList(),
    val refreshStatus: RefreshStatus = RefreshStatus.Show,
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