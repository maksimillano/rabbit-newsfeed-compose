package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.model.attachment.VideoAttachment

data class VideoEntry(
    private val video: VideoAttachment
) : FeedDisplayEntry {
    override val key: String = "Video${video.id}"
    @Composable
    override fun onBind() {
    }
}