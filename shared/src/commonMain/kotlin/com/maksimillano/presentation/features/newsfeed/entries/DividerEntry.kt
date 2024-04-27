package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.maksimillano.api.model.post.newfeed.Feed
import com.maksimillano.presentation.component.colorThemed

data class DividerEntry(
    val feedItem: Feed
) : FeedDisplayEntry {
    override val key: String = "Divider${feedItem.id}"

    @Composable
    override fun onBind() {
        Divider(
            thickness = 1.dp,
            color = colorThemed { it.dividerColor },
        )
    }
}