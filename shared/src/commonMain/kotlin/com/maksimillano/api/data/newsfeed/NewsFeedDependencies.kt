package com.maksimillano.api.data.newsfeed

interface NewsFeedDependencies {
    val postInteractor: PostInteractor

    interface PostInteractor {
        fun savePost(postId: Long, channelId: Long)
        fun setReaction(postId: Long, channelId: Long, reaction: Int)
        fun unsetReaction(postId: Long, channelId: Long)
    }
}