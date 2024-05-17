package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.component.PostImage
import com.maksimillano.presentation.features.newsfeed.ImageSizeCalculator
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.pxToDpValue

data class ImagesRowGridEntry(
    private val imagesRow: ImageSizeCalculator.GridImagesRow,
    private val position: Int,
    private val post: Post
) : FeedDisplayEntry {
    override val key: String = "Photo${post.sourceId}-${post.postId}-${position}"

    @Composable
    override fun onBind() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(imagesRow.height.pxToDpValue(LocalDensity.current))
                .padding(horizontal = Dimens.postMargin)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = Dimens.postPadding)
                .clip(RoundedCornerShape(topStart = Dimens.postCardCornerRadius, topEnd = Dimens.postCardCornerRadius, bottomStart = Dimens.postCardCornerRadius, bottomEnd = Dimens.postCardCornerRadius))
        ) {
            val firstImage = imagesRow.left
            val secondImage = imagesRow.topRight
            val thirdImage = imagesRow.bottomRight

            PostImage(firstImage)
            Column(
                modifier = Modifier.height(imagesRow.height.pxToDpValue(LocalDensity.current))
            ) {
                PostImage(secondImage)
                PostImage(thirdImage)
            }
        }
    }
}