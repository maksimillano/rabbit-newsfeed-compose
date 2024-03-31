package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.model.newsfeed.newfeed.Feed

data class RepostHeaderEntry(
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "RepostHeader${feedItem.sourceId}-${feedItem.date}"
    @Composable
    override fun onBind() {
    }
}