package com.maksimillano.api.domain.features.newsfeed

import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry

interface NewsFeedDisplayEntryFactory {
    fun create(feedHistory: PostHistory): List<FeedDisplayEntry>
}