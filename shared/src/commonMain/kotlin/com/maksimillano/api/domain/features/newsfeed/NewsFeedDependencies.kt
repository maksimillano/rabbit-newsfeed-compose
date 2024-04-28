package com.maksimillano.api.domain.features.newsfeed

import com.maksimillano.api.domain.features.post.PostsLoaderData
import com.maksimillano.api.domain.loader.Loader

interface NewsFeedDependencies {
    val displayEntryFactory: NewsFeedDisplayEntryFactory
    val newsfeedLoader: Loader<PostsLoaderData>
    val postInteractor: PostInteractor

    interface PostInteractor {
        fun savePost(postId: Long, channelId: Long)
        fun setReaction(postId: Long, channelId: Long, reaction: Int)
        fun unsetReaction(postId: Long, channelId: Long)
    }
}