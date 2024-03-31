package com.maksimillano.presentation.features.newsfeed

import androidx.compose.ui.ExperimentalComposeUiApi
import co.touchlab.kermit.Logger
import com.maksimillano.AppConstants
import com.maksimillano.api.model.attachment.PhotoAttachment
import com.maksimillano.api.model.newsfeed.FeedHistory
import com.maksimillano.api.model.newsfeed.newfeed.Feed
import com.maksimillano.api.model.newsfeed.newfeed.PostFeed
import com.maksimillano.presentation.features.newsfeed.entries.*
import com.maksimillano.util.toFormattedTime
import kotlin.math.abs

object FeedDisplayEntryFactory {
    fun create(feedHistory: FeedHistory): List<FeedDisplayEntry> {
        Logger.i(AppConstants.LOGGER_TAG) { "Creating entries: $feedHistory" }
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
        feed: Feed,
        feedHistory: FeedHistory
    ) {

        val ownerInfo: OwnerInfo = if (feed.sourceId < 0) {
            val group = feedHistory.groups.find { it.groupId == abs(feed.sourceId) }!!
            OwnerInfo(group.name, group.photos.first())
        } else {
            val profile = feedHistory.users.find { it.userId == feed.sourceId }!!
            val name = "${profile.firstName} ${profile.lastName}"
            OwnerInfo(name, profile.photos.first())
        }

        when (feed) {
            is PostFeed -> {
                createPostEntry(feedDisplayEntries, feed, feedHistory, ownerInfo)
            }
        }
    }

    private fun createPostEntry(
        feedDisplayEntries: MutableList<FeedDisplayEntry>,
        postFeedItem: PostFeed,
        feedHistory: FeedHistory,
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


        createPostContentEntry(postFeedItem, feedHistory)?.let {
            feedDisplayEntries.add(it)
        }

        createPostBottomEntry(postFeedItem, feedHistory)?.let {
            feedDisplayEntries.add(it)
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    private fun createPostContentEntry(
        postFeedItem: PostFeed,
        feedHistory: FeedHistory
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
                    imageUrl = photoAttachment.thumbnails[0].url ?: "",
                    photo = photoAttachment,
                    feedItem = postFeedItem
                )
            }
        }
        return null
    }

    private fun createPostBottomEntry(postFeedItem: PostFeed, feedHistory: FeedHistory): FeedDisplayEntry? {
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