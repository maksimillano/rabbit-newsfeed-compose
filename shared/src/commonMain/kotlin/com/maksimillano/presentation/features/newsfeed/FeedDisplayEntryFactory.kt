package com.maksimillano.presentation.features.newsfeed

import co.touchlab.kermit.Logger
import com.maksimillano.AppConstants
import com.maksimillano.api.model.attachment.PhotoAttachment
import com.maksimillano.api.model.post.PostHistory
import com.maksimillano.api.model.post.newfeed.Feed
import com.maksimillano.api.model.post.newfeed.PostFeed
import com.maksimillano.presentation.features.newsfeed.entries.*
import com.maksimillano.util.toFormattedTime
import kotlin.math.abs

class FeedDisplayEntryFactory {
    fun create(postHistory: PostHistory): List<FeedDisplayEntry> {
        Logger.i(AppConstants.LOGGER_TAG) { "Creating entries: $postHistory" }
        val feedDisplayEntries: MutableList<FeedDisplayEntry> = mutableListOf()

        if (postHistory.hasBefore) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.TOP))
        }

        postHistory.feeds.forEachIndexed { index, feedItem ->
            createFeedDisplayEntry(feedDisplayEntries, feedItem, postHistory)
//            feedDisplayEntries.add(DividerEntry(feedItem))
        }

        if (postHistory.hasAfter) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.BOTTOM))
        }

        return feedDisplayEntries
    }

    private fun createFeedDisplayEntry(
        feedDisplayEntries: MutableList<FeedDisplayEntry>,
        feed: Feed,
        postHistory: PostHistory
    ) {

        val ownerInfo: OwnerInfo = if (feed.sourceId < 0) {
            val group = postHistory.channels.find { it.peer.publicId == abs(feed.sourceId) }!!
            OwnerInfo(group.name, group.avatar.url)
        } else {
            val profile = postHistory.users.find { it.peer.publicId == feed.sourceId }!!
            val name = "${profile.firstName} ${profile.lastName}"
            OwnerInfo(name, profile.avatar.url)
        }

        when (feed) {
            is PostFeed -> {
                createPostEntry(feedDisplayEntries, feed, postHistory, ownerInfo)
            }
        }
    }

    private fun createPostEntry(
        feedDisplayEntries: MutableList<FeedDisplayEntry>,
        postFeedItem: PostFeed,
        postHistory: PostHistory,
        ownerInfo: OwnerInfo
    ) {
        val list = listOf(
            HeaderEntry(
                title = ownerInfo.name,
                iconUrl = ownerInfo.photo,
                hasUnwatchedStory = false,
                dateFormatted = postFeedItem.date.toFormattedTime(),
                date = postFeedItem.date,
                feedItem = postFeedItem
            ),

            TextEntry(
                text = postFeedItem.text,
                feedItem = postFeedItem
            )
        )
        feedDisplayEntries.addAll(list)


        createPostContentEntry(postFeedItem, postHistory)?.let {
            feedDisplayEntries.add(it)
        }

        createPostBottomEntry(postFeedItem, postHistory)?.let {
            feedDisplayEntries.add(it)
        }
    }

    private fun createPostContentEntry(
        postFeedItem: PostFeed,
        postHistory: PostHistory
    ): FeedDisplayEntry? {
        val attachments = postFeedItem.attachments
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
                    feedItem = postFeedItem
                )
            }
        }
        return null
    }

    private fun createPostBottomEntry(postFeedItem: PostFeed, postHistory: PostHistory): FeedDisplayEntry? {
        val likesInfo = postFeedItem.likesInfo
        val commentsInfo = postFeedItem.commentsInfo
        val repostsInfo = postFeedItem.repostsInfo
        val viewsInfo = postFeedItem.viewsInfo

        return BottomEntry(
            likesCount = likesInfo.count,
            commentsCount = commentsInfo.count,
            repliesCount = repostsInfo.count,
            viewsCount = viewsInfo.count,
            feedItem = postFeedItem
        )
    }

    private data class OwnerInfo(
        val name: String,
        val photo: String
    )
}