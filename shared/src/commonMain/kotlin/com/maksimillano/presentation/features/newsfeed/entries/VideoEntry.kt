package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.domain.model.attachment.VideoAttachment
import com.maksimillano.api.domain.model.post.Post

data class VideoEntry(
    private val video: VideoAttachment,
    private val post: Post
) : FeedDisplayEntry {
    override val key: String = "Video${post.sourceId}-${post.postId}-${video.id}"
    @Composable
    override fun onBind() {
    }
}