package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.newsfeed.NewsFeedDependencies
import com.maksimillano.presentation.features.newsfeed.DefaultFeedDisplayEntryFactory

class NewsFeedDependenciesImpl : NewsFeedDependencies {
    override val displayEntryFactory = DefaultFeedDisplayEntryFactory()
    override val newsfeedLoader = DefaultNewsFeedLoader()
    override val postInteractor = PostInteractorImpl()

    class PostInteractorImpl : NewsFeedDependencies.PostInteractor {
        override fun savePost(postId: Long, channelId: Long) {
        }

        override fun setReaction(postId: Long, channelId: Long, reaction: Int) {
        }

        override fun unsetReaction(postId: Long, channelId: Long) {
        }
    }
}