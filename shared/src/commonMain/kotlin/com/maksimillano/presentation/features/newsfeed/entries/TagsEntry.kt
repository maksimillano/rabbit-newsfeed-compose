package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.model.newsfeed.newfeed.Feed

data class TagsEntry(
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "Tags${feedItem.sourceId}-${feedItem.date}"
    @Composable
    override fun onBind() {
    }
}