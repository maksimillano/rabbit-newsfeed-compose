package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.component.dimenThemed

data class TextEntry(
    private val text: String,
    private val feedItem: Post
) : FeedDisplayEntry {

    override val key: String = "Text${feedItem.sourceId}-${feedItem.postId}"

    @Composable
    override fun onBind() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .background(MaterialTheme.colorScheme.surface),
                fontSize = dimenThemed { it.postContent }
            )
        }
    }
}