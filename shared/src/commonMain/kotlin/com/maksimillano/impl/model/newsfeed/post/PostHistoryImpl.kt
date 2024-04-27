package com.maksimillano.impl.model.newsfeed.post

import com.maksimillano.api.model.profile.Channel
import com.maksimillano.api.model.profile.User
import com.maksimillano.api.model.post.PostHistory
import com.maksimillano.api.model.post.newfeed.Feed

data class PostHistoryImpl(
    override val feeds: List<Feed> = emptyList(),
    override val users: List<User> = emptyList(),
    override val channels: List<Channel> = emptyList(),
    override val hasAfter: Boolean = true,
    override val hasBefore: Boolean = true
) : PostHistory {
    fun isEmpty() = feeds.isEmpty()
    fun isNotEmpty() = feeds.isNotEmpty()
}