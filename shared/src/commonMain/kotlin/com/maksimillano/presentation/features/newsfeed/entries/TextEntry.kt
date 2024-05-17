package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.features.newsfeed.EntryPart
import com.maksimillano.presentation.theme.Dimens
import com.maksimillano.util.pxToDp

data class TextEntry(
    private val text: String,
    private val post: Post,
    private val height: Int,
    private val textPart: EntryPart,
) : FeedDisplayEntry {

    override val key: String = "Text${post.sourceId}-${post.postId}-${textPart.hashCode()}"

    @Composable
    override fun onBind() {
        val verticalPadding = when (textPart) {
            is EntryPart.Full -> PaddingValues(vertical = Dimens.postTextVerticalMargin, horizontal = Dimens.postPadding)
            is EntryPart.Top -> PaddingValues(top = Dimens.postTextVerticalMargin, start = Dimens.postPadding, end = Dimens.postPadding)
            is EntryPart.Bottom -> PaddingValues(bottom = Dimens.postTextVerticalMargin, start = Dimens.postPadding, end = Dimens.postPadding)
            is EntryPart.Middle -> PaddingValues(horizontal = Dimens.postPadding)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.pxToDp())
                .padding(horizontal = Dimens.postMargin)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = Dimens.postPadding),
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterStart),
                fontSize = Dimens.postTextSize
            )
        }
    }
}