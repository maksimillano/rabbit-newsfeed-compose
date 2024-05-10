package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.component.PostImage
import com.maksimillano.presentation.features.newsfeed.EntryPart
import com.maksimillano.presentation.features.newsfeed.ImageSizeCalculator
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.pxToDpValue

data class ImagesRowEntry(
    private val imagesRow: ImageSizeCalculator.SimpleImagesRow,
    private val entryPart: EntryPart,
    private val post: Post
) : FeedDisplayEntry {
    override val key: String = "Photo${post.sourceId}-${post.postId}-${entryPart.hashCode()}"

    @Composable
    override fun onBind() {
        val imageShape = when (entryPart) {
            is EntryPart.Full -> RoundedCornerShape(topStart = Dimens.postImageCornerRadius, topEnd = Dimens.postImageCornerRadius, bottomStart = Dimens.postImageCornerRadius, bottomEnd = Dimens.postImageCornerRadius)
            is EntryPart.Top -> RoundedCornerShape(topStart = Dimens.postImageCornerRadius, topEnd = Dimens.postImageCornerRadius)
            is EntryPart.Middle -> RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
            is EntryPart.Bottom -> RoundedCornerShape(bottomStart = Dimens.postImageCornerRadius, bottomEnd = Dimens.postImageCornerRadius)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(imagesRow.height.pxToDpValue(LocalDensity.current))
                .padding(horizontal = Dimens.postMargin)
                .clip(imageShape)
        ) {
            for (imageSpec in imagesRow.images) {
                PostImage(imageSpec)
            }
        }
    }
}