package com.maksimillano.api.model.newsfeed

import com.maksimillano.api.model.Group
import com.maksimillano.api.model.User
import com.maksimillano.api.model.newsfeed.newfeed.Feed

interface FeedHistory {
    val feeds: List<Feed>
    val users: List<User>
    val groups: List<Group>
    val hasAfter: Boolean
    val hasBefore: Boolean
}