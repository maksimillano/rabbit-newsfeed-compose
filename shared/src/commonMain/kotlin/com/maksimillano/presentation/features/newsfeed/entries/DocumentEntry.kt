package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.domain.model.attachment.DocumentAttachment
import com.maksimillano.api.domain.model.post.Post

data class DocumentEntry(
    private val document: DocumentAttachment,
    private val post: Post
) : FeedDisplayEntry {

    override val key: String = "Document${post.sourceId}-${post.postId}-${document.id}"
    @Composable
    override fun onBind() {
    }
}