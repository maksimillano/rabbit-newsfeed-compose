package com.maksimillano.api.proxy.newsfeed

import kotlinx.coroutines.flow.Flow

interface NewsFeedLoader {
    val events: Flow<NewsFeedLoaderEvent>

    val hasHistoryBefore: Boolean
    val hasHistoryAfter: Boolean
    val isLoading: Boolean

    suspend fun loadLatest()
    suspend fun loadSince(weight: Long)
    suspend fun loadBefore(weight: Long)
}