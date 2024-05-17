package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.theme.Dimens

data class HeaderPostEntry(
    private val title: String,
    private val dateFormatted: String,
    private val date: Long,
    private val post: Post
) : FeedDisplayEntry {
    override val key: String = "Header${post.sourceId}-${post.postId}"

    @Composable
    override fun onBind() {
        val cornerShape = RoundedCornerShape(
            topStart = Dimens.postCardCornerRadius,
            topEnd = Dimens.postCardCornerRadius
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(Dimens.postHeaderPostBarHeight)
                .padding(horizontal = Dimens.postMargin)
                .background(MaterialTheme.colorScheme.surface, cornerShape)
                .padding(horizontal = Dimens.postPadding)
            ,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = Dimens.postHeaderPostTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
            )
            Text(
                dateFormatted,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = Dimens.postHeaderTime,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}