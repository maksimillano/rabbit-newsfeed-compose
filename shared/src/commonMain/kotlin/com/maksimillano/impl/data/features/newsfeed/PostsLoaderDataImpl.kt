package com.maksimillano.impl.data.features.newsfeed

import com.maksimillano.api.domain.features.post.PostsLoaderData
import com.maksimillano.api.domain.model.post.PostHistory

data class PostsLoaderDataImpl(override val postHistory: PostHistory) : PostsLoaderData {
    override val size: Int
        get() = postHistory.feeds.size
    override val hasExpired: Boolean
        get() = postHistory.hasBefore // TODO
}