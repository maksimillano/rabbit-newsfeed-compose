package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.api.domain.features.newsfeed.NewsFeedDisplayEntryFactory

class NewsFeedDependenciesImpl(
    feedDisplayEntryFactory: NewsFeedDisplayEntryFactory
) : NewsFeedDependencies {
    override val displayEntryFactory = feedDisplayEntryFactory
    override val newsfeedLoader = DefaultNewsFeedLoader()
    override val postInteractor = PostInteractorImpl()
    override val pageItemsCount: Int = 25

    class PostInteractorImpl : NewsFeedDependencies.PostInteractor {
        override fun savePost(postId: Long, channelId: Long) {
        }

        override fun setReaction(postId: Long, channelId: Long, reaction: Int) {
        }

        override fun unsetReaction(postId: Long, channelId: Long) {
        }
    }
}