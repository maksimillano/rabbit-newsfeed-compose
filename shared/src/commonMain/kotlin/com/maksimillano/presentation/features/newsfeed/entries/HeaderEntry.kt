package com.maksimillano.presentation.features.newsfeed.entries

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.component.backgroundThemed
import com.maksimillano.presentation.component.colorThemed
import com.maksimillano.presentation.component.dimenThemed
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class HeaderEntry(
    private val title: String,
    private val iconUrl: String,
    private val hasUnwatchedStory: Boolean,
    private val dateFormatted: String,

//    private val emojiStatus: String, // 1
//    private val isVerified: Boolean, // 2
//    private val isConfirmed: Boolean, // 2
    private val date: Long, // 3
//    private val isPinned: Boolean, // 4
//
//    private val forFriends: Boolean,
//    private val forCloseFriends: Boolean,
//    private val forDonuts: Boolean,
//
//    private val sourceName: String, // Bloom
//    private val pinnedFrom: String, // от Галины
//    private val isInterestPost: Boolean, // Интересная запись
//    private val isGovernment: Boolean, // Госорганизация
//    private val fromAuthor: String,
//    private val isMarkedOnPhoto: Boolean, // Отмечена на фото
//    private val isPhotoUpdated: Boolean, // Обновлено фото

    private val post: Post
) : FeedDisplayEntry {

    override val key: String = "Header${post.sourceId}-${post.postId}"

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun onBind() {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .backgroundThemed { it.backgroundBarColor }
                .padding(horizontal = 14.dp)
        ) {
            Row(
                Modifier
                    .fillMaxHeight()
                    .weight(1f, fill = true)
            ) {
//                val painter = asyncPainterResource(
//                    data = "https://alialbaali.com/images/Kamel.png"
//                )

//                KamelImage(
//                    resource = painter,
//                    contentDescription = "Image: $key",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .align(Alignment.CenterVertically)
//                        .padding(end = 14.dp)
//                        .size(30.dp)
//                        .clip(CircleShape)
//                        .border(
//                            width = 0.5.dp,
//                            color = colorThemed { it.borderColor },
//                            shape = CircleShape
//                        )
//                )

                Text(
                    text = title,
                    color = colorThemed { it.textColor },
                    fontSize = dimenThemed { it.postTitle },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )

                Text(
                    dateFormatted,
                    color = colorThemed { it.subtitleColor },
                    fontSize = dimenThemed { it.hint },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }


//            val painter = painterResource(MR.images.menu_dots)
//            Image(
//                painter = painter,
//                contentDescription = "Image menu",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .size(20.dp),
//                colorFilter = ColorFilter.tint(
//                    colorThemed { it.subtitleColor }
//                ),
//            )
        }
    }
}