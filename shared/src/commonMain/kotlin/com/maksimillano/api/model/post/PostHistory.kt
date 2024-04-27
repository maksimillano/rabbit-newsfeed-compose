package com.maksimillano.api.model.post

import com.maksimillano.api.model.profile.Channel
import com.maksimillano.api.model.profile.User
import com.maksimillano.api.model.post.newfeed.Feed

interface PostHistory {
    val feeds: List<Feed>
    val users: List<User>
    val channels: List<Channel>
    val hasAfter: Boolean
    val hasBefore: Boolean
}