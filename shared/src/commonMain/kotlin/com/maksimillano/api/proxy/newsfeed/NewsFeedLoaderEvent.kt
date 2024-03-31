package com.maksimillano.api.proxy.newsfeed

import com.maksimillano.api.model.newsfeed.FeedHistory

sealed interface NewsFeedLoaderEvent {
    data class FeedLoadedEvent(
        val isActual: Boolean,
        val feedHistory: FeedHistory
    ) : NewsFeedLoaderEvent
    data class HasPrefetchedEvent(val prefetched: Int) : NewsFeedLoaderEvent
    data class FailedEvent(val reason: String) : NewsFeedLoaderEvent
}