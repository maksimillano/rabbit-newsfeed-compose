package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.component.dimenThemed
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

data class BottomEntry(
    private val likesCount: Int,
    private val commentsCount: Int,
    private val repliesCount: Int,
    private val viewsCount: Int,
    private val reaction: String? = null,

//    private val canLike: Boolean,
//    private val canComment: Boolean,
//    private val canReply: Boolean,
//    private val viewsVisible: Boolean,
    private val feedItem: Post,
) : FeedDisplayEntry {
    override val key: String = "Bottom${feedItem.sourceId}-${feedItem.postId}"

    @Composable
    override fun onBind() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PostActionBar()
            ViewsInfo()
        }
    }

    @Composable
    private fun RowScope.PostActionBar() {
//        Row(
//            modifier = Modifier
//                .fillMaxHeight()
//                .align(Alignment.CenterVertically)
//        ) {
//            ActionButton(
//                imageResource = MR.images.like,
//                description = "Likes",
//                counter = likesCount,
//                modifier = Modifier.padding(end = 6.dp),
//                onClick = {}
//            )
//            ActionButton(
//                imageResource = MR.images.comment,
//                description = "Comments",
//                counter = commentsCount,
//                modifier = Modifier.padding(end = 6.dp),
//                onClick = {}
//            )
//            ActionButton(
//                imageResource = MR.images.repost,
//                description = "Replies",
//                counter = repliesCount,
//                onClick = {}
//            )
//        }
    }

    @Composable
    private fun RowScope.ActionButton(
        imageResource: ImageResource,
        description: String,
        counter: Int,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Row (
            modifier = modifier
                .align(Alignment.CenterVertically)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = description,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(24.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "$counter",
                color = MaterialTheme.colorScheme.primary,
                fontSize = dimenThemed { it.textSmall },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }

    }

    @Composable
    private fun RowScope.ViewsInfo() {
//        Row(
//            modifier = Modifier
//                .fillMaxHeight()
//        ) {
//            Image(
//                painter = painterResource(MR.images.view),
//                contentDescription = "Views",
//                colorFilter = ColorFilter.tint(colorThemed { it.subtitleColor }),
//                modifier = Modifier.size(18.dp)
//                    .align(Alignment.CenterVertically)
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Text(
//                text = "$viewsCount",
//                color = colorThemed { it.subtitleColor },
//                fontSize = dimenThemed { it.textSmall },
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(end = 5.dp),
//            )
//        }
    }
}