package com.maksimillano.presentation.features.newsfeed

import com.maksimillano.api.model.newsfeed.FeedHistory
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

data class NewsFeedState(
    val feedItemEntries: List<FeedDisplayEntry> = emptyList(),
    val refreshStatus: RefreshStatus = RefreshStatus.Show,
    val updateNotification: UpdateNotification = UpdateNotification.Missing,
    val error: Error = Error.Missing
)

sealed interface RefreshStatus {
    data object Show : RefreshStatus
    data object Missing : RefreshStatus
}

sealed interface UpdateNotification {
    data object Show : UpdateNotification
    data object Missing : UpdateNotification
}

sealed interface Error {
    data object Unknown : Error
    data object NetworkLost : Error
    data object Missing : Error
}

internal typealias NewsFeedPartialState = (NewsFeedState) -> NewsFeedState

object NewsFeedMutations {
    fun empty(): NewsFeedPartialState = { previousState ->
        previousState.copy()
    }

    fun onActualResult(history: FeedHistory): NewsFeedPartialState = { previousState ->
        previousState.copy(
            feedItemEntries = FeedDisplayEntryFactory.create(history),
            refreshStatus = RefreshStatus.Missing,

            // One time shots
            error = Error.Missing
        )
    }

    fun onCacheResult(history: FeedHistory): NewsFeedPartialState = { previousState ->
        previousState.copy(
            feedItemEntries = FeedDisplayEntryFactory.create(history),
            refreshStatus = RefreshStatus.Show,

            // One time shots
            error = Error.Missing

        )
    }

    fun onFailure(errorType: Error): NewsFeedPartialState = { previousState ->
        previousState.copy(
            error = errorType
        )
    }
}