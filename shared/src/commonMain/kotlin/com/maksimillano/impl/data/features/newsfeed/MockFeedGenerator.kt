package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.model.account.Peer
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.api.domain.model.profile.Channel
import com.maksimillano.api.domain.model.profile.User
import com.maksimillano.impl.domain.ChannelImpl
import com.maksimillano.impl.domain.attachment.PhotoAttachmentImpl
import com.maksimillano.impl.domain.newsfeed.post.PostHistoryImpl
import com.maksimillano.impl.domain.newsfeed.newsfeed.PostImpl
import com.maksimillano.impl.domain.newsfeed.post.image.ImageSizeableImpl
import kotlin.random.Random

object MockFeedGenerator {
    fun generate(): PostHistory {
        val feeds: MutableList<Postable> = mutableListOf()
        val users: MutableList<User> = mutableListOf()
        val channels: MutableList<Channel> = mutableListOf()

        val channel = ChannelImpl(
            peer = Peer.Channel(-1, 1),
            name = "Channel Name Sample",
            shortName = "channel_name_short",
            avatar = ImageSizeableImpl(100, 100, "https://flager.ru/84/25.jpg"),
            photos = listOf(ImageSizeableImpl(100, 100, "https://flager.ru/84/25.jpg"))
        )
        channels.add(channel)
        val feedCount = 10
        val images = listOf(
//            ImageSizeableImpl(657, 512, "https://free-images.com/md/e1b0/calidris_alba_bird_nature.jpg"),
//            ImageSizeableImpl(1083, 512, "https://free-images.com/md/ca59/sunrise_sea_coast_horizon.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
            ImageSizeableImpl(342, 512, "https://free-images.com/md/b214/sunset_sunrise_sunlight_outdoor_5.jpg"),
//            ImageSizeableImpl(341, 512, "https://free-images.com/md/f4e9/sunset_sunrise_sunlight_outdoor_4.jpg"),
//            ImageSizeableImpl(910, 512, "https://free-images.com/md/da71/sunset_sunrise_sunlight_outdoor_1.jpg"),
        )
        val random = Random(12)
        val randomPhoto = Random(2)
        repeat(feedCount) { index ->
            val imageCount = random.nextInt(1, 11)
            val attaches = Array(imageCount) { attachIndex ->
                PhotoAttachmentImpl(
                    id = attachIndex.toLong(),
                    ownerId = 1L,
                    date = attachIndex.toLong(),
                    text = "",
                    thumbnail = images.random(randomPhoto)
                )
            }

            feeds.add(
                PostImpl(
                    id = "$index",
                    date = index.toLong(),
                    text = "Some text $index",
                    sourceId = channel.peer.publicId,
                    postId = index.toLong(),
                    owner = channel.peer,
                    attachments = attaches.toList()
                )
            )
        }

        return PostHistoryImpl(
            feeds = feeds,
            users = users,
            channels = channels
        )
    }
}