package com.maksimillano.presentation.features.newsfeed

import co.touchlab.kermit.Logger
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory
import com.maksimillano.api.domain.model.attachment.PhotoAttachment
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.features.newsfeed.entries.BottomEntry
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry
import com.maksimillano.presentation.features.newsfeed.entries.HeaderEntry
import com.maksimillano.presentation.features.newsfeed.entries.ImagesRowEntry
import com.maksimillano.presentation.features.newsfeed.entries.ImagesRowGridEntry
import com.maksimillano.presentation.features.newsfeed.entries.ProgressEntry
import com.maksimillano.presentation.features.newsfeed.entries.TextEntry
import com.maksimillano.util.BuildConstants
import com.maksimillano.util.toFormattedTime

class DefaultFeedDisplayEntryFactory(
    private val imageSizeCalculator: ImageSizeCalculator
) : NewsFeedDisplayEntryFactory {

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


        createPostContentEntry(postItem, postHistory, feedDisplayEntries)

//        createPostBottomEntry(postItem, postHistory)?.let {
//            feedDisplayEntries.add(it)
//        }
    }

    private fun createPostContentEntry(
        postItem: Post,
        postHistory: PostHistory,
        feedDisplayEntries: MutableList<FeedDisplayEntry>
    ) {
        val attachments = postItem.attachments
        if (attachments.isNotEmpty()) {
            val photoAttachments = attachments.filterIsInstance<PhotoAttachment>()

            val imagesRows = mutableListOf<ImageSizeCalculator.ImagesRow>()
            imageSizeCalculator.process(
                photoAttachments.map { it.thumbnail },
                imagesRows
            )

            imagesRows.forEachIndexed { index, row ->
                val entryPart = when {
                    index == 0 && imagesRows.size == 1 -> EntryPart.Full
                    index == 0 -> EntryPart.Top
                    index == imagesRows.lastIndex -> EntryPart.Bottom
                    else -> EntryPart.Middle(index - 1)
                }

                val entry =  when (row) {
                    is ImageSizeCalculator.GridImagesRow -> {
                        ImagesRowGridEntry(row, index, postItem)
                    }
                    is ImageSizeCalculator.SimpleImagesRow -> {
                        ImagesRowEntry(row, entryPart, postItem)
                    }
                }
                feedDisplayEntries.add(entry)
            }
        }
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