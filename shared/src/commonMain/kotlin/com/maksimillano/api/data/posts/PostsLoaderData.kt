package com.maksimillano.api.data.posts

import com.maksimillano.api.model.post.PostHistory
import com.maksimillano.api.data.loader.LoaderData

data class PostsLoaderData(val postHistory: PostHistory) : LoaderData {
    override val size: Int
        get() = postHistory.feeds.size
    override val hasExpired: Boolean
        get() = postHistory.hasBefore // TODO
}