package com.maksimillano.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.maksimillano.presentation.features.newsfeed.ImageSizeCalculator
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.pxToDpValue

@Composable
fun PostImage(imageSpec: ImageSizeCalculator.SuitableThumb) {
    val width = imageSpec.measuredWidth.pxToDpValue(LocalDensity.current)
    val height = imageSpec.measuredHeight.pxToDpValue(LocalDensity.current)
    AppImage(
        uri = imageSpec.thumb.urlBestQuality(imageSpec.measuredWidth),
        contentDescription = "Post Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(width)
            .height(height)
            .background(MaterialTheme.colorScheme.surface)
            .border(width = 0.5.dp, color = MaterialTheme.colorScheme.surface)
            .clip(
                RoundedCornerShape(
                    topStart = Dimens.postImageThumbCornerRadius,
                    topEnd = Dimens.postImageThumbCornerRadius,
                    bottomStart = Dimens.postImageThumbCornerRadius,
                    bottomEnd = Dimens.postImageThumbCornerRadius
                )
            )
    )
}