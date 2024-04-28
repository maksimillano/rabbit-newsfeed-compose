package com.maksimillano.presentation.features.newsfeed

import co.touchlab.kermit.Logger
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory
import com.maksimillano.util.BuildConstants
import com.maksimillano.api.domain.model.attachment.PhotoAttachment
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.presentation.features.newsfeed.entries.*
import com.maksimillano.util.toFormattedTime

class DefaultFeedDisplayEntryFactory : NewsFeedDisplayEntryFactory {
    override fun create(feedHistory: PostHistory): List<FeedDisplayEntry> {
        Logger.i(BuildConstants.LOGGER_TAG) { "Creating entries: $feedHistory" }
        val feedDisplayEntries: MutableList<FeedDisplayEntry> = mutableListOf()

        if (feedHistory.hasBefore) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.TOP))
        }

        feedHistory.feeds.forEachIndexed { index, feedItem ->
            createFeedDisplayEntry(feedDisplayEntries, feedItem, feedHistory)
//            feedDisplayEntries.add(DividerEntry(feedItem))
        }

        if (feedHistory.hasAfter) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.BOTTOM))
        }

        return feedDisplayEntries
    }

    private fun createFeedDisplayEntry(
        feedDisplayEntries: MutableList<FeedDisplayEntry>,
        feed: Postable,
        postHistory: PostHistory
    ) {

        val ownerInfo: OwnerInfo = if (feed.sourceId < 0) {
            val group = postHistory.channels.find { it.peer.publicId == feed.sourceId }!!
            OwnerInfo(group.name, group.avatar.url)
        } else {
            val profile = postHistory.users.find { it.peer.publicId == feed.sourceId }!!
            val name = "${profile.firstName} ${profile.lastName}"
            OwnerInfo(name, profile.avatar.url)
        }

        when (feed) {
            is Post -> {
                createPostEntry(feedDisplayEntries, feed, postHistory, ownerInfo)
            }
        }
    }

    private fun createPostEntry(
        feedDisplayEntries: MutableList<FeedDisplayEntry>,
        postItem: Post,
        postHistory: PostHistory,
        ownerInfo: OwnerInfo
    ) {
        val list = listOf(
            HeaderEntry(
                title = ownerInfo.name,
                iconUrl = ownerInfo.photo,
                hasUnwatchedStory = false,
                dateFormatted = postItem.date.toFormattedTime(),
                date = postItem.date,
                post = postItem
            ),

            TextEntry(
                text = postItem.text,
                feedItem = postItem
            )
        )
        feedDisplayEntries.addAll(list)


        createPostContentEntry(postItem, postHistory)?.let {
            feedDisplayEntries.add(it)
        }

        createPostBottomEntry(postItem, postHistory)?.let {
            feedDisplayEntries.add(it)
        }
    }

    private fun createPostContentEntry(
        postItem: Post,
        postHistory: PostHistory
    ): FeedDisplayEntry? {
        val attachments = postItem.attachments
        if (!attachments.isNullOrEmpty()) {
            val photoAttachment = attachments.filterIsInstance<PhotoAttachment>().firstOrNull()
            if (photoAttachment != null) {
//                val thumbnails = photoAttachment.thumbnails!!
//                val result = mutableListOf<ImageProcessor.SuitableThumb>()
//                ImageProcessor.process(
//                    listOf(thumbnails),
//                    ScreenWidth,
//                    result
//                )

                return PhotosEntry(
                    imageUrl = photoAttachment.thumbnail.url,
                    photo = photoAttachment,
                    post = postItem
                )
            }
        }
        return null
    }

    private fun createPostBottomEntry(postItem: Post, postHistory: PostHistory): FeedDisplayEntry? {
        val likesInfo = postItem.likesInfo
        val commentsInfo = postItem.commentsInfo
        val repostsInfo = postItem.repostsInfo
        val viewsInfo = postItem.viewsInfo

        return BottomEntry(
            likesCount = likesInfo.count,
            commentsCount = commentsInfo.count,
            repliesCount = repostsInfo.count,
            viewsCount = viewsInfo.count,
            feedItem = postItem
        )
    }

    private data class OwnerInfo(
        val name: String,
        val photo: String
    )
}