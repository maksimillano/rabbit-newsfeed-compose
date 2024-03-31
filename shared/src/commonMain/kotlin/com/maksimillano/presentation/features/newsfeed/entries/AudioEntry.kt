package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.model.attachment.AudioAttachment
import com.maksimillano.api.model.newsfeed.newfeed.Feed

data class AudioEntry(
    private val audio: AudioAttachment,
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "Audio${audio.id}"

    @Composable
    override fun onBind() {
    }
}