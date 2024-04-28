package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.domain.model.post.Post

data class TagsEntry(
    private val post: Post
) : FeedDisplayEntry {

    override val key: String = "Tags${post.sourceId}-${post.postId}"
    @Composable
    override fun onBind() {
    }
}