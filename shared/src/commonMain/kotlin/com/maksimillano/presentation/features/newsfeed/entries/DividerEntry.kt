package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.component.colorThemed

data class DividerEntry(
    val feedItem: Postable
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