package com.maksimillano.impl.domain.newsfeed.post

import com.maksimillano.api.domain.model.profile.Channel
import com.maksimillano.api.domain.model.profile.User
import com.maksimillano.api.domain.model.post.PostHistory
import com.maksimillano.api.domain.model.post.Postable

data class PostHistoryImpl(
    override val feeds: List<Postable> = emptyList(),
    override val users: List<User> = emptyList(),
    override val channels: List<Channel> = emptyList(),
    override val hasAfter: Boolean = false,
    override val hasBefore: Boolean = true
) : PostHistory {
    fun isEmpty() = feeds.isEmpty()
    fun isNotEmpty() = feeds.isNotEmpty()
}