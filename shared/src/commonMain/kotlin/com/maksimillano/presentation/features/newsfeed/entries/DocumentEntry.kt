package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.runtime.Composable
import com.maksimillano.api.model.attachment.DocumentAttachment
import com.maksimillano.api.model.newsfeed.newfeed.Feed

data class DocumentEntry(
    private val document: DocumentAttachment,
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "Document${document.id}"
    @Composable
    override fun onBind() {
    }
}