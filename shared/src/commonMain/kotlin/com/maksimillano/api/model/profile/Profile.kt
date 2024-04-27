package com.maksimillano.api.model.profile

import com.maksimillano.api.model.account.Peer
import com.maksimillano.api.model.post.image.ImageSizeable

sealed interface Profile {
    val peer: Peer
    val name: String
    val about: String
    val avatar: ImageSizeable
    val photos: List<ImageSizeable>
}