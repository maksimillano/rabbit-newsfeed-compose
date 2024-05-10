package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.component.AppImage
import com.maksimillano.presentation.theme.Dimens

data class HeaderEntry(
    private val title: String,
    private val iconUrl: String,
    private val dateFormatted: String,
    private val date: Long,
    private val post: Post
) : FeedDisplayEntry {
    override val key: String = "Header${post.sourceId}-${post.postId}"

    @Composable
    override fun onBind() {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(Dimens.postHeaderBarHeight)
//                .backgroundThemed { it.backgroundBarColor }
                .padding(horizontal = Dimens.postMargin),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                AppImage(
                    uri = iconUrl,
                    contentDescription = "Image: $key",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 14.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(
                            width = 0.5.dp,
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = CircleShape
                        )
                )

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
//                    fontSize = dimenThemed { it.postTitle },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )

                Text(
                    dateFormatted,
//                    color = colorThemed { it.subtitleColor },
//                    fontSize = dimenThemed { it.hint },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}