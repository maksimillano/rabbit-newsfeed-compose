package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.domain.model.attachment.AudioAttachment
import com.maksimillano.api.domain.model.post.Post

data class AudioEntry(
    private val audio: AudioAttachment,
    private val post: Post
) : FeedDisplayEntry {

    override val key: String = "Audio${post.sourceId}-${post.postId}-${audio.id}"

    @Composable
    override fun onBind() {
    }
}