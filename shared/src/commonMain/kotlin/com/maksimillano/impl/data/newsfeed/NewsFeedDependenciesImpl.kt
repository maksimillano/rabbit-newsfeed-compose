package com.maksimillano.impl.data.newsfeed

import com.maksimillano.api.data.newsfeed.NewsFeedDependencies

class NewsFeedDependenciesImpl : NewsFeedDependencies {
    override val postInteractor: NewsFeedDependencies.PostInteractor = PostInteractorImpl()

    class PostInteractorImpl : NewsFeedDependencies.PostInteractor {
        override fun savePost(postId: Long, channelId: Long) {
        }

        override fun setReaction(postId: Long, channelId: Long, reaction: Int) {
        }

        override fun unsetReaction(postId: Long, channelId: Long) {
        }
    }
}