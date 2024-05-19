package com.maksimillano.presentation.features.newsfeed

import co.touchlab.kermit.Logger
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory
import com.maksimillano.api.domain.model.attachment.PhotoAttachment
import com.maksimillano.api.domain.model.post.Post
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.api.domain.model.post.Postable
import com.maksimillano.presentation.features.newsfeed.entries.BottomEntry
import com.maksimillano.presentation.features.newsfeed.entries.DividerEntry
import com.maksimillano.presentation.features.newsfeed.entries.FeedDisplayEntry
import com.maksimillano.presentation.features.newsfeed.entries.HeaderNewsfeedEntry
import com.maksimillano.presentation.features.newsfeed.entries.HeaderPostEntry
import com.maksimillano.presentation.features.newsfeed.entries.ImagesRowEntry
import com.maksimillano.presentation.features.newsfeed.entries.ImagesRowGridEntry
import com.maksimillano.presentation.features.newsfeed.entries.PostRoundCornerBottomEntry
import com.maksimillano.presentation.features.newsfeed.entries.ProgressEntry
import com.maksimillano.presentation.features.newsfeed.entries.TextEntry
import com.maksimillano.util.BuildConstants
import com.maksimillano.util.toFormattedTime

class DefaultFeedDisplayEntryFactory(
    private val imageSizeCalculator: ImageSizeCalculator,
    private val textCalculator: TextCalculator
) : NewsFeedDisplayEntryFactory {

    override fun create(feedHistory: PostHistory): List<FeedDisplayEntry> {
        Logger.i(BuildConstants.LOGGER_TAG) { "Creating entries: $feedHistory" }
        val feedDisplayEntries: MutableList<FeedDisplayEntry> = mutableListOf()

        feedDisplayEntries.add(DividerEntry(DIVIDER_TOP_ID))
        if (feedHistory.hasAfter) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.TOP))
        }

        feedHistory.feeds.forEachIndexed { index, post ->
            createFeedDisplayEntry(feedDisplayEntries, post, feedHistory)
            val dividerId = "${post.id}-${post.sourceId}"
            feedDisplayEntries.add(DividerEntry(dividerId))
        }

        if (feedHistory.hasBefore) {
            feedDisplayEntries.add(ProgressEntry(ProgressEntry.Gravity.BOTTOM))
        }
        feedDisplayEntries.add(DividerEntry(DIVIDER_BOTTOM_ID))

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
        post: Post,
        postHistory: PostHistory,
        ownerInfo: OwnerInfo
    ) {
        feedDisplayEntries.add(
            HeaderNewsfeedEntry(
                title = ownerInfo.name,
                iconUrl = ownerInfo.photo,
                dateFormatted = post.date.toFormattedTime(),
                date = post.date,
                post = post
            )
//            HeaderPostEntry(
//                title = ownerInfo.name,
//                dateFormatted = post.date.toFormattedTime(),
//                date = post.date,
//                post = post
//            )
        )

        createPostContentEntry(post, postHistory, feedDisplayEntries)

        val results = textCalculator.calculate(post.text)
        val entries = results.mapIndexed { index, result ->
            TextEntry(
                text = result.text,
                height = result.height,
                post = post,
                textPart = entryPart(index, results)
            )
        }
        feedDisplayEntries.addAll(entries)
        feedDisplayEntries.add(PostRoundCornerBottomEntry(post))



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
            imageSizeCalculator.calculate(
                photoAttachments.map { it.thumbnail },
                imagesRows
            )

            imagesRows.forEachIndexed { index, row ->
                val entryPart = entryPart(index, imagesRows)

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

    private fun entryPart(
        index: Int,
        imagesRows: List<*>
    ): EntryPart {
        val entryPart = when {
            index == 0 && imagesRows.size == 1 -> EntryPart.Full
            index == 0 -> EntryPart.Top
            index == imagesRows.lastIndex -> EntryPart.Bottom
            else -> EntryPart.Middle(index - 1)
        }
        return entryPart
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

    private companion object {
        const val DIVIDER_TOP_ID = "_TOP"
        const val DIVIDER_BOTTOM_ID = "_BOTTOM"
    }
}