package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.theme.Dimens

data class DividerEntry(
    val post: Postable
) : FeedDisplayEntry {
    override val key: String = "Divider${post.id}-${post.sourceId}"

    @Composable
    override fun onBind() {
        Divider(
            thickness = Dimens.postDivider,
            color = MaterialTheme.colorScheme.background,
        )
    }
}