package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.maksimillano.api.model.attachment.PhotoAttachment
import com.maksimillano.api.model.post.newfeed.Feed
import com.maksimillano.presentation.component.backgroundThemed
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

data class PhotosEntry(
    private val imageUrl: String,
    private val photo: PhotoAttachment,
    private val feedItem: Feed
) : FeedDisplayEntry {

    override val key: String = "Photo${photo.id}"
    @Composable
    override fun onBind() {
        val painter = asyncPainterResource(
            data = this@PhotosEntry.imageUrl
        )

        KamelImage(
            resource = painter,
            contentDescription = "Image: $key",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .backgroundThemed { it.backgroundBarColor }
        )

//        val painter = painterResource(imageUrl)
//        Image(
//            painter = painter,
//            contentDescription = "Image: $key",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxWidth()
//                .backgroundThemed { it.backgroundBarColor }
//        )
    }
}