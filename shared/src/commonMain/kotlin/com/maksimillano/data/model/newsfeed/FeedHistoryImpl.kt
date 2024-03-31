package com.maksimillano.data.model.newsfeed

import com.maksimillano.api.model.Group
import com.maksimillano.api.model.User
import com.maksimillano.api.model.newsfeed.FeedHistory
import com.maksimillano.api.model.newsfeed.newfeed.Feed

data class FeedHistoryImpl(
    override val feeds: List<Feed> = emptyList(),
    override val users: List<User> = emptyList(),
    override val groups: List<Group> = emptyList(),
    override val hasAfter: Boolean = true,
    override val hasBefore: Boolean = true
) : FeedHistory {
    fun isEmpty() = feeds.isEmpty()
    fun isNotEmpty() = feeds.isNotEmpty()
}