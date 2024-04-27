package com.maksimillano.impl.data.newsfeed

import com.maksimillano.api.model.account.Peer
import com.maksimillano.api.model.post.PostHistory
import com.maksimillano.api.model.post.image.ImageSizeable
import com.maksimillano.api.model.post.newfeed.Feed
import com.maksimillano.api.model.profile.Channel
import com.maksimillano.api.model.profile.User
import com.maksimillano.impl.model.ChannelImpl
import com.maksimillano.impl.model.attachment.PhotoAttachmentImpl
import com.maksimillano.impl.model.newsfeed.post.PostHistoryImpl
import com.maksimillano.impl.model.newsfeed.newsfeed.PostFeedImpl
import com.maksimillano.impl.model.newsfeed.post.image.ImageSizeableImpl

object MockFeedGenerator {
    fun generate(): PostHistory {
        val feeds: MutableList<Feed> = mutableListOf()
        val users: MutableList<User> = mutableListOf()
        val channels: MutableList<Channel> = mutableListOf()

        val channel = ChannelImpl(
            peer = Peer.Channel(1, 1),
            name = "Channel Name Sample",
            shortName = "channel_name_short",
            avatar = ImageSizeableImpl(100, 100, "https://flager.ru/84/25.jpg"),
            photos = listOf(ImageSizeableImpl(100, 100, "https://flager.ru/84/25.jpg"))
        )
        channels.add(channel)
        val feedCount = 10
        repeat(feedCount) { index ->
            feeds.add(
                PostFeedImpl(
                    id = "$index",
                    date = index.toLong(),
                    text = "Some text $index",
                    sourceId = -channel.peer.publicId,
                    postId = index.toLong(),
                    ownerId = -channel.peer.publicId,
                    attachments = listOf(
                        PhotoAttachmentImpl(
                            id = index.toLong(),
                            ownerId = 1L,
                            date = 1L,
                            text = "",
                            thumbnail = ImageSizeableImpl(100, 100, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStM8VBkIAWtKmRXBRO6L2Q6luEc6e30FzH3Spk6TV-uSPTb7wPyTBCk2MfwwdhHKOjkkw&usqp=CAU")
                        )
                    )
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