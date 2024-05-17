package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.theme.Dimens

data class PostRoundCornerBottomEntry(
    private val post: Postable
) : FeedDisplayEntry {
    override val key: String
        get() = "PostRoundCornerBottomEntry${post.id}-${post.sourceId}"

    @Composable
    override fun onBind() {
        val roundedCornerShape = RoundedCornerShape(
            bottomStart = Dimens.postCardCornerRadius,
            bottomEnd = Dimens.postCardCornerRadius
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.postCardCornerRadius)
                .padding(horizontal = Dimens.postMargin)
                .background(MaterialTheme.colorScheme.surface, roundedCornerShape)
                .padding(horizontal = Dimens.postPadding)
        )
    }
}