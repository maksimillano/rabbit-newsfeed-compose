package com.maksimillano.api.domain.model.post

import com.maksimillano.api.domain.model.profile.Channel
import com.maksimillano.api.domain.model.profile.User

interface PostHistory {
    val feeds: List<Postable>
    val users: List<User>
    val channels: List<Channel>
    val hasAfter: Boolean
    val hasBefore: Boolean
}